<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Analytics Dashboard</title>
    
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .dashboard-container { max-width: 1200px; margin: auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .chart-container { width: 100%; margin-bottom: 40px; border: 1px solid #ddd; padding: 10px; background-color: #fafafa; }
        h2 { color: #333; margin-top: 0; }
        .summary-stats { display: flex; gap: 20px; margin-bottom: 20px; }
        .stat-card { flex: 1; padding: 15px; text-align: center; background-color: #e9ecef; border-radius: 4px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .stat-card h3 { margin: 0; color: #495057; }
        .stat-card p { font-size: 24px; font-weight: bold; color: #007bff; margin: 5px 0 0; }
        .chart-container canvas { max-width: 400px; max-height: 300px; margin: 0 auto; }
    </style>
</head>
<body>
<div class="dashboard-container">
    <h1>Admin Analytics Dashboard</h1>

    
    <div class="summary-stats">
        <div class="stat-card"><h3>Total Clubs</h3><p id="totalClubs">Loading...</p></div>
        <div class="stat-card"><h3>Total Members</h3><p id="totalMembers">Loading...</p></div>
        <div class="stat-card"><h3>Total Events</h3><p id="totalEvents">Loading...</p></div>
        <div class="stat-card"><h3>Avg. Rating</h3><p id="avgRating">Loading...</p></div>
        <div class="stat-card"><h3>Total Feedback</h3><p id="totalFeedback">Loading...</p></div>
    </div>

   
    <div class="chart-container"><h2>Monthly Events</h2><canvas id="monthlyEventsChart"></canvas></div>
    <div class="chart-container"><h2>Popular Events (By Attendance)</h2><canvas id="popularEventsChart"></canvas></div>
    <div class="chart-container"><h2>Top-Rated Events</h2><canvas id="topRatedEventsChart"></canvas></div>
    <div class="chart-container"><h2>Feedback Rating Distribution</h2><canvas id="ratingDistributionChart"></canvas></div>
</div>

<script>
    
    const baseUrl = '<%=request.getContextPath()%>/analytics';

    
    function loadSummaryStats() {
        fetch(baseUrl + '?data=summary')
            .then(r => r.json())
            .then(data => {
                document.getElementById('totalClubs').textContent = data.totalClubs ?? '0';
                document.getElementById('totalMembers').textContent = data.totalMembers ?? '0';
                document.getElementById('totalEvents').textContent = data.totalEvents ?? '0';
            })
            .catch(() => {
                document.getElementById('totalClubs').textContent = 'Error';
                document.getElementById('totalMembers').textContent = 'Error';
                document.getElementById('totalEvents').textContent = 'Error';
            });

        fetch(baseUrl + '?data=overallRating')
            .then(r => r.json())
            .then(data => {
                document.getElementById('avgRating').textContent = data.averageRating ? data.averageRating.toFixed(2) : '0.00';
                document.getElementById('totalFeedback').textContent = data.totalFeedbackCount ?? '0';
            })
            .catch(() => {
                document.getElementById('avgRating').textContent = 'Error';
                document.getElementById('totalFeedback').textContent = 'Error';
            });
    }

    
    function loadMonthlyEventsChart() {
        fetch(baseUrl + '?data=monthlyEvents')
            .then(r => r.json())
            .then(data => {
                const ctx = document.getElementById('monthlyEventsChart').getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: Object.keys(data),
                        datasets: [{
                            label: 'Number of Events',
                            data: Object.values(data),
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: { responsive: true, scales: { y: { beginAtZero: true } } }
                });
            });
    }

   
    function loadPopularEventsChart() {
        fetch(baseUrl + '?data=popularEvents')
            .then(r => r.json())
            .then(data => {
                const ctx = document.getElementById('popularEventsChart').getContext('2d');
                new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        labels: Object.keys(data),
                        datasets: [{
                            label: 'Attendance Count',
                            data: Object.values(data),
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: { responsive: true, plugins: { legend: { position: 'top' } } }
                });
            });
    }

    
    function loadTopRatedEventsChart() {
        fetch(baseUrl + '?data=topRatedEvents')
            .then(r => r.json())
            .then(data => {
                const ctx = document.getElementById('topRatedEventsChart').getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: Object.keys(data),
                        datasets: [{
                            label: 'Average Rating (1-5)',
                            data: Object.values(data),
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: { responsive: true, scales: { y: { beginAtZero: true, max: 5 } } }
                });
            });
    }

   
    function loadRatingDistributionChart() {
        fetch(baseUrl + '?data=ratingDistribution')
            .then(r => r.json())
            .then(data => {
                const fullData = {};
                for (let i = 1; i <= 5; i++) fullData[i] = data[i] || 0;

                const ctx = document.getElementById('ratingDistributionChart').getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['1 Star', '2 Stars', '3 Stars', '4 Stars', '5 Stars'],
                        datasets: [{
                            label: 'Number of Feedbacks',
                            data: Object.values(fullData),
                            backgroundColor: 'rgba(153, 102, 255, 0.2)',
                            borderColor: 'rgba(153, 102, 255, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: { responsive: true, scales: { y: { beginAtZero: true } } }
                });
            });
    }

    
    window.addEventListener('load', function() {
        loadSummaryStats();
        loadMonthlyEventsChart();
        loadPopularEventsChart();
        loadTopRatedEventsChart();
        loadRatingDistributionChart();
    });
</script>
</body>
</html>
