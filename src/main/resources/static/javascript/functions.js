$(document).ready(function () {
    $('.sidenav').sidenav();
})

$('.carousel.carousel-slider').carousel({
    fullWidth: true,
    indicators: true,
    pressed: true
});

$(document).ready(function () {
    $('#carousel-content').carousel();
    setInterval(function () {
        $('#carousel-content').carousel('next');
    }, 5000);
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


})
/*
$(document).ready(function () {
    /!*var url = '/particular/refreshJornada';
    if ($('#servicioSelect').val() != ''){
        url = url + '/' + $('#servicioSelect').val();
    }
    $("#servicioSelect").load(url);*!/
    /!*if ($('#servicioSelect').val() != '') {
        $("#jornadaSelect").load("refreshJornada", $('#servicioSelect').serialize());
    }*!/

    //call function when page is loaded
    getContent();

    //set on change listener
    $('#servicioSelect').change(getContent);

    function getContent() {

        //create url to request fragment
        if ($('#servicioSelect').val() != '') {
            //load fragment and replace content
            $('#jornadaSelect').load("refreshJornada", $('#servicioSelect').serialize());
            //$("#inputJornada").load(" #inputJornada");

            //$('#inputJornada').load(' #inputJornada');
        }
    }
})*/
