console.log("you entered dashboard");
$(document).ready(function(){
    const z = {
        "to":localStorage.getItem("to"),
        "token":localStorage.getItem("token")
    }
    console.log(z);
    $.ajax({
         type: "POST",
         contentType: "application/json",
         url: "/verifyToken",
         data: JSON.stringify(z),
         dataType: 'json',

         success: function (data){
              console.log("hello success");
              console.log(data.statusCode)
              if(data.statusCode == "202"){
                    console.log("status code is 202 :)");
                    alert('YOU NEED TO LOGIN FIRST!');
                    window.location = '/login';
              }
         }
    });
});