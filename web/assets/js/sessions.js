
/*

$(() => {
    $('#courses-filter-code, #courses-filter-name, #courses-filter-date').typeWatch({callback: loadCourses});
    $('#courses-filter-location').change(() => {
        loadCourses()
    });

    $.getJSON('/api/locations', (data) => {
        data.forEach((row) => {
            $('#courses-filter-location').append(`<option value="${row.id}">${row.city}</option>`);
        })
    });
});

 */