// ================================================
//  admin.js — Logic for admin.html (Admin Panel)
// ================================================

// Load all bookings when page is ready
window.addEventListener('DOMContentLoaded', function () {
    loadAdminBookings();
});

/**
 * Fetches all bookings from GetAllBookings servlet
 * and renders them with a Delete button per row.
 */
function loadAdminBookings() {
    document.getElementById('loading').style.display = 'flex';
    document.getElementById('tbl-wrap').style.display = 'none';
    document.getElementById('empty-box').style.display = 'none';
    document.getElementById('count-badge').textContent = 'loading…';
    hideAdminMsg();

    fetch('GetAllBookings')             // GET → GetAllBookingsServlet
        .then(function (res) { return res.json(); })
        .then(function (bookings) {
            document.getElementById('loading').style.display = 'none';

            // Update stats
            var booked = bookings.length;
            document.getElementById('stat-booked').textContent = booked;
            document.getElementById('stat-avail').textContent = 10 - booked;
            document.getElementById('count-badge').textContent = booked + ' record(s)';

            if (booked === 0) {
                document.getElementById('empty-box').style.display = 'block';
                return;
            }

            // Build table rows
            var tbody = document.getElementById('admin-tbody');
            tbody.innerHTML = '';

            bookings.forEach(function (b, index) {
                var row = document.createElement('tr');
                row.id = 'row-' + b.id;   // Used to remove row after delete
                row.innerHTML =
                    '<td>' + (index + 1) + '</td>' +
                    '<td><span class="seat-badge">' + b.seat_no + '</span></td>' +
                    '<td>' + escapeHtml(b.user_name) + '</td>' +
                    '<td class="time-txt">' + formatTime(b.booking_time) + '</td>' +
                    '<td>' +
                    '<button class="btn btn-danger" ' +
                    'onclick="deleteBooking(' + b.id + ', \'' + b.seat_no + '\')">' +
                    '🗑️ Delete' +
                    '</button>' +
                    '</td>';
                tbody.appendChild(row);
            });

            document.getElementById('tbl-wrap').style.display = 'block';
        })
        .catch(function (err) {
            console.error('Error loading admin bookings:', err);
            document.getElementById('loading').style.display = 'none';
            document.getElementById('count-badge').textContent = 'error';
        });
}

/**
 * Sends a DELETE request to DeleteBooking servlet to remove a booking.
 * Removes the row from the table dynamically without full page reload.
 *
 * @param {number} id     - The booking's database ID
 * @param {string} seatNo - Seat number (used in confirmation message)
 */
function deleteBooking(id, seatNo) {
    // Ask user to confirm before deleting
    var confirmed = window.confirm(
        'Delete booking for seat ' + seatNo + '?\nThis cannot be undone.'
    );
    if (!confirmed) return;

    fetch('DeleteBooking?id=' + id, {   // DELETE → DeleteBookingServlet
        method: 'DELETE'
    })
        .then(function (res) { return res.json(); })
        .then(function (data) {
            if (data.status === 'success') {
                showAdminMsg('success', '✅ Seat ' + seatNo + ' booking removed.');

                // Remove the row from the DOM without reloading
                var row = document.getElementById('row-' + id);
                if (row) row.remove();

                // Update stats counters
                var booked = parseInt(document.getElementById('stat-booked').textContent) - 1;
                document.getElementById('stat-booked').textContent = booked;
                document.getElementById('stat-avail').textContent = 10 - booked;
                document.getElementById('count-badge').textContent = booked + ' record(s)';

                // Show empty state if no rows left
                var tbody = document.getElementById('admin-tbody');
                if (tbody.children.length === 0) {
                    document.getElementById('tbl-wrap').style.display = 'none';
                    document.getElementById('empty-box').style.display = 'block';
                }
            } else {
                showAdminMsg('error', '❌ ' + data.message);
            }
        })
        .catch(function (err) {
            console.error('Delete error:', err);
            showAdminMsg('error', '❌ Connection error. Is Tomcat running?');
        });
}

/** Shows an admin status message. Auto-hides after 5 seconds. */
function showAdminMsg(type, text) {
    var box = document.getElementById('admin-msg');
    document.getElementById('admin-msg-text').textContent = text;
    box.className = 'msg show ' + type;
    setTimeout(hideAdminMsg, 5000);
}

/** Hides the admin message bar. */
function hideAdminMsg() {
    document.getElementById('admin-msg').className = 'msg';
}

/** Prevents XSS by escaping HTML special characters. */
function escapeHtml(str) {
    if (!str) return '';
    return str
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;');
}

/** Formats MySQL timestamp to readable local date-time. */
function formatTime(ts) {
    if (!ts) return '—';
    try {
        var d = new Date(ts.replace(' ', 'T'));
        return d.toLocaleString('en-IN', {
            day: '2-digit', month: '2-digit', year: 'numeric',
            hour: '2-digit', minute: '2-digit', second: '2-digit'
        });
    } catch (e) { return ts; }
}
