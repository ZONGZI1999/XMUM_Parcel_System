$().ready(function(){
    //导航栏
    $('#myParcel').click(function () {
        window.location.href = 'myParcel';
    });
    $('#parcelQuery').click(function (){
        window.location.href = 'parcelQuery';
    })
    $('#parcelPickUp').click(function(){
        window.location.href = 'parcelPickUp';
    });

})