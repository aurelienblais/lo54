(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

let updateURI = (data, location) => {
    window.history.replaceState({ data },
        'Super Duper School',
        window.document.location.origin + '/' + location + ($.param(data) ? '?' + $.param(data) : '')
    );
};

let getURI = () => {
    if (document.location.search !== "")
        return JSON.parse('{"' + decodeURI(document.location.search.substr(1)).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"').replace(/\s/g,'') + '"}')
};

let getPath = () => {
    return document.location.pathname.substr(1);
};