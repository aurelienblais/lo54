const COURSE_ROW_TEMPLATE = Handlebars.compile($('#course-row-template').html());
const COURSE_ITEM_TEMPLATE = Handlebars.compile($('#course-item-template').html());
const COURSE_NEW_TEMPLATE = Handlebars.compile($('#course-new-template').html());
const COURSE_EDIT_TEMPLATE = Handlebars.compile($('#course-edit-template').html());

let loadCourses = () => {
    $('#courses-container > .col-12').fadeToggle(100);
    $('#courses-list').empty();
    const filters = {
        code: $('#courses-filter-code').val(),
        title: $('#courses-filter-name').val(),
        date: $('#courses-filter-date').val(),
        city: $('#courses-filter-location').val()
    };
    $.getJSON('/api/courses', filters, (data) => {
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

let editCourse = (id) => {
    $('.modal').modal('hide');
    $('.modal').remove();
    $.getJSON(`/api/courses/${id}`, (data) => {
        $('body').append(COURSE_EDIT_TEMPLATE(data));
        $('#course-edit-modal').modal('show');
    });
};

let deleteCourse = (id) => {
    if (confirm("Are you sure?")) {
        $.ajax({
            type: "DELETE",
            url: "/api/courses/" + id,
            success: function(data)
            {
                toastr.success('Course deleted', 'Course successfully deleted');
                loadCourses();
            },
            error: function() {
                toastr.error('Course deletion failed')
            }
        });
    }
};

$('[data-toggle="new-course"]').click( () => {
   $('#course-new-modal').remove();
   $('body').append(COURSE_NEW_TEMPLATE);
   $('#course-new-modal').modal('show');
});



let submitNewCourseForm = () => {
    $form = $('#course-form');
    $.ajax({
        type: "POST",
        url: $form.attr('action'),
        data: $form.serialize(),
        success: function(data)
        {
            $('.modal').modal('hide');
            toastr.success('Course creation', 'Course successfully created');
            loadCourses();
        },
        error: function() {
            toastr.error('Check if code is not already used.', 'Course creation failed')
        }
    });
};

let submitEditCourseForm = () => {
    $form = $('#edit-course-form');
    $.ajax({
        type: "PATCH",
        url: $form.attr('action'),
        data: JSON.stringify($form.serializeFormJSON()),
        dataType: 'json',
        success: function(data)
        {
            $('.modal').modal('hide');
            toastr.success('Course edit', 'Course successfully edited');
            loadCourses();
        },
        error: function() {
            toastr.error('Course edit failed')
        }
    });
};

$(() => {
    $('#courses-filter-code, #courses-filter-name, #courses-filter-date').typeWatch({callback: loadCourses});
    $('#courses-filter-location').change(() => {
        loadCourses()
    });
});