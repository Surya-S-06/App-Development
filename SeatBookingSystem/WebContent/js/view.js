// ================================================
//  view.js — Logic for view.html (View All Bookings)
// ================================================

// Load bookings as soon as the page is ready
window.addEventListener('DOMContentLoaded', function () {
    loadBookings();
});

/**
 * Fetches all bookings from GetAllBookings servlet
 * and renders them into the table.
 */
function loadBookings() {
    // Show loading, hide table and empty state
    document.getElementById('loading').style.display = 'flex';
    document.getElementById('tbl-wrap').style.display = 'none';
    document.getElementById('empty-box').style.display = 'none';
    document.getElementById('count-badge').textContent = 'loading…';

    fetch('GetAllBookings')             // GET → GetAllBookingsServlet
        .then(function (res) { return res.json(); })
        .then(function (bookings) {
            document.getElementById('loading').style.display = 'none';

            if (bookings.length === 0) {
                // No bookings yet
                document.getElementById('empty-box').style.display = 'block';
                document.getElementById('count-badge').textContent = '0 bookings';
                return;
            }

            // Populate table rows
            var tbody = document.getElementById('view-tbody');
            tbody.innerHTML = '';   // Clear previous rows

            bookings.forEach(function (b, index) {
                var row = document.createElement('tr');
                row.innerHTML =
                    '<td>' + (index + 1) + '</td>' +
                    '<td><span class="seat-badge">' + b.seat_no + '</span></td>' +
                    '<td>' + escapeHtml(b.user_name) + '</td>' +
                    '<td class="time-txt">' + formatTime(b.booking_time) + '</td>';
                tbody.appendChild(row);
            });

            document.getElementById('tbl-wrap').style.display = 'block';
            document.getElementById('count-badge').textContent = bookings.length + ' booking(s)';
        })
        .catch(function (err) {
            console.error('Error loading bookings:', err);
            document.getElementById('loading').style.display = 'none';
            document.getElementById('count-badge').textContent = 'error';
        });
}

/**
 * Formats a timestamp string from MySQL into a readable local time.
 * e.g. "2024-03-30 21:05:00.0" → "30/03/2024, 09:05:00 PM"
 */
function formatTime(ts) {
    if (!ts) return '—';
    try {
        var d = new Date(ts.replace(' ', 'T'));
        return d.toLocaleString('en-IN', {
            day: '2-digit', month: '2-digit', year: 'numeric',
            hour: '2-digit', minute: '2-digit', second: '2-digit'
        });
    } catch (e) {
        return ts;
    }
}

/**
 * Prevents XSS by escaping HTML special characters in user-provided text.
 */
function escapeHtml(str) {
    if (!str) return '';
    return str
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;');
}
