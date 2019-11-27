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
    loadCourses();


    $.getJSON('/api/locations', (data) => {
        data.forEach((row) => {
            $('.location-filter').append(`<option value="${row.id}">${row.city}</option>`);
        })
    });

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        switch(e.target.innerHTML.toLowerCase()) {
            case "courses":
                loadCourses();
                break;
            case "sessions":
                loadSessions()
                break;
            default:
                break;
        }
    });
});

