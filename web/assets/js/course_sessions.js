const COURSE_SESSION_ROW_TEMPLATE = Handlebars.compile($('#course-session-row-template').html());
const COURSE_SESSION_REGISTER_TEMPLATE = Handlebars.compile($('#course-session-create-template').html());
const COURSE_SESSION_NEW_TEMPLATE = Handlebars.compile($('#course-session-new-template').html());

let registerCourseSession = (id) => {
    $('body').append(COURSE_SESSION_REGISTER_TEMPLATE({code: id}));
    $('#course-session-modal').modal('show')
};

let submitCourseSessionForm = () => {
    $form = $('#course-session-form');
    $.ajax({
        type: "POST",
        url: $form.attr('action'),
        data: $form.serialize(),
        success: function (data) {
            $('.modal').modal('hide');
            toastr.success('Session registration', 'You are now registered for this session');
        }
    });
};

$('[data-toggle="new-course-session"]').click(() => {
    $.getJSON('/api/courses', (courses) => {
       $.getJSON('/api/locations', (locations) => {
           $('#course-session-new-modal').remove();
           $('body').append(COURSE_SESSION_NEW_TEMPLATE({
               courses: courses,
               locations: locations
           }));
           $('#course-session-new-modal').modal('show');

       });
    });
});

let submitNewCourseSessionForm = () => {
    $form = $('#new-course-session-form');
    $.ajax({
        type: "POST",
        url: $form.attr('action'),
        data: $form.serialize(),
        success: function (data) {
            $('.modal').modal('hide');
            toastr.success('Course session creation', 'Course session successfully created');
            loadSessions();
        },
        error: function () {
            toastr.error('Course session creation failed')
        }
    });
};

let loadSessions = () => {
    uri = getURI();

    if (uri) {
        $('#course-sessions-filter-code').val(uri.code);
        $('#course-sessions-filter-date').val(uri.start_date);
        $('#course-sessions-filter-location').val(uri.location);
    }

    $('#sessions-container > .col-12').fadeToggle(100);
    $('#sessions-list').empty();
    const filters = {
        code: $('#course-sessions-filter-code').val(),
        start_date: $('#course-sessions-filter-date').val(),
        city: $('#course-sessions-filter-location').val()
    };
    $.getJSON('/api/course_sessions', filters, (data) => {
        data.forEach((row) => {
            $('#sessions-list').append(COURSE_SESSION_ROW_TEMPLATE(row));
        });
        $('#sessions-container > .col-12').fadeToggle(100);
    });
};

let searchSessions = () => {
    updateURI({
        code: $('#course-sessions-filter-code').val(),
        start_date: $('#course-sessions-filter-date').val(),
        city: $('#course-sessions-filter-location').val()
    }, 'course_sessions');
    loadSessions();
};

$(() => {
    $('#course-sessions-filter-code, #course-sessions-filter-date').typeWatch({callback: searchSessions});
    $('#course-sessions-filter-location').change(() => {
        searchSessions()
    });
});