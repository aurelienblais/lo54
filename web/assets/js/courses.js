const COURSE_ROW_TEMPLATE = Handlebars.compile($('#course-row-template').html());
const COURSE_ITEM_TEMPLATE = Handlebars.compile($('#course-item-template').html());
const COURSE_NEW_TEMPLATE = Handlebars.compile($('#course-new-template').html());

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

$(() => {
    $('#courses-filter-code, #courses-filter-name, #courses-filter-date').typeWatch({callback: loadCourses});
    $('#courses-filter-location').change(() => {
        loadCourses()
    });
});