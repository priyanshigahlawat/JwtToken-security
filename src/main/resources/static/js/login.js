console.log("you entered login");
$(document).ready(function(){

    $("#login2").click(function(){
        let to = $(".loginId").val();
        const z = {
            "to" : $(".loginId").val(),
        }
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/sendSms",
            data: JSON.stringify(z),
            dataType: 'json',


            success: function (data){
                if(data.statusCode == "202"){
                    $("#remark2").html(data.message);
                } else if(data.statusCode == "200"){
                    localStorage.setItem("to",to);
                    localStorage.setItem("token",data.token);
                    $("#verify").prop("disabled",false);
                    $(".qwe").css("display", "block");
                    $("#remark2").html(data.message);
                }
            }
        });
    });

    $("#verify").click(function(){
        $(".remark2").html("");
        const z = {
            "to" : $(".loginId").val(),
            "otp" : $(".loginPass").val()
        }
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/verifyOtp",
            data: JSON.stringify(z),
            dataType: 'json',

            success: function (data){
                if(data.statusCode == "202")
                    $("#remark2").html(data.message);
                else{
                    window.location = '/dashboard';
                }
            }
        });
    });
});