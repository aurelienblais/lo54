Handlebars.registerHelper('dateFormat', function (context, block) {
    return new Date(context).toLocaleString('fr-FR');
});

Handlebars.registerHelper('percentage', function (p1, p2, block) {
    return (p1 / p2) * 100;
});

$(() => {
    loadCourses();
});