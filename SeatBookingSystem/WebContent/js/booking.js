// ================================================
//  booking.js — Logic for index.html (Seat Booking)
// ================================================

// Tracks current selected seat (e.g. "A1", "B3")
let selectedSeat = null;

// ── On page load: fetch already-booked seats ────────
window.addEventListener('DOMContentLoaded', function () {
    loadBookedSeats();
});

/**
 * Calls the GetBookedSeats servlet to get the list of booked seat numbers,
 * then marks each one RED and disabled on the UI.
 */
function loadBookedSeats() {
    fetch('GetBookedSeats')           // GET request → GetBookedSeatsServlet
        .then(function (res) { return res.json(); })
        .then(function (bookedList) {
            // bookedList is an array like ["A1", "B3"]
            bookedList.forEach(function (seatNo) {
                markBooked(seatNo);
            });
            // Update stat counters
            document.getElementById('stat-booked').textContent = bookedList.length;
            document.getElementById('stat-avail').textContent  = 10 - bookedList.length;
        })
        .catch(function (err) {
            console.error('Could not load booked seats:', err);
        });
}

/**
 * Marks a seat button as BOOKED (red, disabled, no click).
 * @param {string} seatNo - e.g. "A2"
 */
function markBooked(seatNo) {
    var btn = document.getElementById('seat-' + seatNo);
    if (!btn) return;
    btn.classList.remove('available', 'selected');
    btn.classList.add('booked');
    btn.disabled  = true;
    btn.onclick   = null;        // Remove the onclick handler
}

/**
 * Called when user clicks a seat button.
 * Toggles the selection — clicking the same seat again deselects it.
 * @param {string} seatNo - e.g. "B4"
 */
function selectSeat(seatNo) {
    var btn = document.getElementById('seat-' + seatNo);

    // Guard: booked seats cannot be selected
    if (!btn || btn.classList.contains('booked')) return;

    // Deselect previously chosen seat
    if (selectedSeat && selectedSeat !== seatNo) {
        var prev = document.getElementById('seat-' + selectedSeat);
        if (prev) {
            prev.classList.remove('selected');
            prev.classList.add('available');
        }
    }

    // Toggle off if clicking same seat
    if (selectedSeat === seatNo) {
        btn.classList.remove('selected');
        btn.classList.add('available');
        selectedSeat = null;
        document.getElementById('sel-display').classList.remove('show');
        document.getElementById('book-btn').disabled = true;
        return;
    }

    // Select new seat
    btn.classList.remove('available');
    btn.classList.add('selected');
    selectedSeat = seatNo;

    // Update the "Selected:" tag and enable the Book button
    document.getElementById('sel-seat-txt').textContent = seatNo;
    document.getElementById('sel-display').classList.add('show');
    document.getElementById('book-btn').disabled = false;

    hideMsg();
}

/**
 * Called when user clicks "Book Seat".
 * Sends a POST request to BookSeat servlet with seat_no and user_name.
 */
function bookSeat() {
    var name = document.getElementById('user-name').value.trim();

    // ── Client-side validation ──────────────────────
    if (!selectedSeat) {
        showMsg('error', '⚠️ Please select a seat first.');
        return;
    }
    if (!name) {
        showMsg('error', '⚠️ Please enter your name.');
        document.getElementById('user-name').focus();
        return;
    }

    // Disable button while request is in flight
    var btn = document.getElementById('book-btn');
    btn.disabled     = true;
    btn.textContent  = '⏳ Booking…';

    // Build form-encoded body (required for request.getParameter() in servlet)
    var body = 'seat_no=' + encodeURIComponent(selectedSeat) +
               '&user_name=' + encodeURIComponent(name);

    fetch('BookSeat', {                          // POST → BookSeatServlet
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: body
    })
    .then(function (res) { return res.json(); })
    .then(function (data) {
        btn.textContent = '🎫 Book Seat';

        if (data.status === 'success') {
            showMsg('success', '✅ ' + data.message);

            // Mark seat permanently booked in the UI
            markBooked(selectedSeat);

            // Update stats
            var booked = parseInt(document.getElementById('stat-booked').textContent) + 1;
            document.getElementById('stat-booked').textContent = booked;
            document.getElementById('stat-avail').textContent  = 10 - booked;

            // Reset form state
            selectedSeat = null;
            document.getElementById('sel-display').classList.remove('show');
            document.getElementById('user-name').value = '';

        } else {
            // Seat already booked or other error
            showMsg('error', '❌ ' + data.message);
            btn.disabled = false;
        }
    })
    .catch(function (err) {
        console.error('Booking error:', err);
        showMsg('error', '❌ Connection error. Is Tomcat running?');
        btn.disabled    = false;
        btn.textContent = '🎫 Book Seat';
    });
}

/** Shows a success or error message bar. Auto-hides after 5 seconds. */
function showMsg(type, text) {
    var box = document.getElementById('msg-box');
    document.getElementById('msg-text').textContent = text;
    box.className = 'msg show ' + type;
    setTimeout(hideMsg, 5000);
}

/** Hides the message bar. */
function hideMsg() {
    document.getElementById('msg-box').className = 'msg';
}
