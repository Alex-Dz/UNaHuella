$(document).ready(function () {
    $('.sidenav').sidenav();
})

$(document).ready(function(){
    $('select').formSelect();
});

$(document).ready(function(){
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