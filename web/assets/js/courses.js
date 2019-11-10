const COURSE_ROW_TEMPLATE = Handlebars.compile($('#course-row-template').html());
const COURSE_ITEM_TEMPLATE = Handlebars.compile($('#course-item-template').html());
;

let loadCourses = () => {
    $.getJSON('/api/courses', (data) => {
        data.forEach((row) => {
            $('#courses-list').append(COURSE_ROW_TEMPLATE(row));
        });
    });
};

let showCourse = (id) => {
    $('.modal').modal('hide');
    $('.modal').remove();
    $.getJSON(`/api/courses/${id}`, (data) => {
        $('body').append(COURSE_ITEM_TEMPLATE(data));
        $('#course-modal').modal('show');
    });
};