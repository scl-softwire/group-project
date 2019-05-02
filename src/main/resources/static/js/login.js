$("button#login-button").click( function() {
    $.ajax({
        url: "/login",
        type: "post",
        data: $("form#login-form").serialize(),
        success : function (response) {
        }
    })
});
