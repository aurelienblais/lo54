const COURSE_ROW_TEMPLATE = Handlebars.compile($('#course-row-template').html());
const COURSE_ITEM_TEMPLATE = Handlebars.compile($('#course-item-template').html());


let loadCourses = () => {
    $('#courses-container > .col-12').fadeToggle(100);
    $('#courses-list').empty();
    $.getJSON('/api/courses', {
        code: $('#courses-filter-code').val(),
        title: $('#courses-filter-name').val()
    }, (data) => {
        data.forEach((row) => {
            $('#courses-list').append(COURSE_ROW_TEMPLATE(row));
        });
        $('#courses-container > .col-12').fadeToggle(100);
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

$(() => {
    $('#courses-filter-code, #courses-filter-name').typeWatch({callback: loadCourses});
});