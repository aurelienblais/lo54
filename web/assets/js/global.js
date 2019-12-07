Handlebars.registerHelper('dateFormat', function (context, block) {
    return new Date(context).toLocaleString('fr-FR');
});

Handlebars.registerHelper('percentage', function (p1, p2, block) {
    return (p1 / p2) * 100;
});

Handlebars.registerHelper('ifCond', function (v1, operator, v2, options) {

    switch (operator) {
        case '==':
            return (v1 == v2) ? options.fn(this) : options.inverse(this);
        case '===':
            return (v1 === v2) ? options.fn(this) : options.inverse(this);
        case '!=':
            return (v1 != v2) ? options.fn(this) : options.inverse(this);
        case '!==':
            return (v1 !== v2) ? options.fn(this) : options.inverse(this);
        case '<':
            return (v1 < v2) ? options.fn(this) : options.inverse(this);
        case '<=':
            return (v1 <= v2) ? options.fn(this) : options.inverse(this);
        case '>':
            return (v1 > v2) ? options.fn(this) : options.inverse(this);
        case '>=':
            return (v1 >= v2) ? options.fn(this) : options.inverse(this);
        case '&&':
            return (v1 && v2) ? options.fn(this) : options.inverse(this);
        case '||':
            return (v1 || v2) ? options.fn(this) : options.inverse(this);
        default:
            return options.inverse(this);
    }
});

$(() => {
    let path = getPath() || 'courses';
    $('.nav-link').removeClass('active');
    $('[href="#' + path + '"]').click().addClass('active');


    $.getJSON('/api/locations', (data) => {
        data.forEach((row) => {
            $('.location-filter').append(`<option value="${row.id}">${row.city}</option>`);
        })
    });

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $('.nav-link').removeClass('active');
        $('[href="#' + e.target.innerHTML.toLocaleLowerCase() + '"]').addClass('active');
        switch(e.target.innerHTML.toLowerCase()) {
            case "courses":
                loadCourses(true);
                updateURI(getURI(), 'courses');
                break;
            case "sessions":
                loadSessions(true);
                updateURI(getURI(), 'course_sessions');
                break;
            default:
                loadCourses();
                break;
        }
    });
});

