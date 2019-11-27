const COURSE_SESSION_ROW_TEMPLATE = Handlebars.compile($('#course-session-row-template').html());
const COURSE_SESSION_REGISTER_TEMPLATE = Handlebars.compile($('#course-session-create-template').html());

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
        success: function(data)
        {
            $('.modal').modal('hide');
            toastr.success('Session registration', 'You are now registered for this session');
        }
    });
};

let loadSessions = () => {
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

$(() => {
    $('#course-sessions-filter-code, #course-sessions-filter-date').typeWatch({callback: loadSessions});
    $('#course-sessions-filter-location').change(() => {
        loadSessions()
    });
});