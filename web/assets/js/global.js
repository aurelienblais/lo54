Handlebars.registerHelper('dateFormat', function (context, block) {
    return new Date(context).toLocaleString('fr-FR')
});

$(() => {
    loadCourses();
});