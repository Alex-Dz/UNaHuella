$(document).ready(function () {
    $('.sidenav').sidenav();
});

$(document).ready(function () {
    $('.carousel.carousel-slider').carousel({
        fullWidth: true,
        indicators: true,
        duration: 250
    });

    setInterval(function () {
        $('#carousel-content').carousel('next');
    }, 10000);
});

$(document).ready(function () {
    $('.slider').slider({full_width: true});
});

$(document).ready(function () {
    $('select').formSelect();
});

$(document).ready(function () {
    $('.datepicker').datepicker({
        firstDay: 1,
        format: 'dd/mm/yyyy'
    });
});

$(document).ready(function () {
    $(".dropdawn").dropdown({
        inDuration: 300,
        inDuration: 500,
        constrainWidth: false,
        coverTrigger: false,
        hover: true
    });

    $('.dropdown').dropdown({
        inDuration: 300,
        inDuration: 500,
        constrainWidth: false,
        coverTrigger: false
    });


});

