$(function () {
    /*点击登录按钮时*/
    $('#btnRegister').click(function () {
        $('#r_name~.error:nth-of-type(2)').hide();

        <!--表单验证-->
        var isValid = true;

        var name = $('#r_name').val();
        var email = $('#email').val();
        var password = $('#r_password').val();

        var nameReg = /^[a-zA-Z]{1,3}$/;
        if (!nameReg.test(name)) {
            $('#r_name+.error').show();
            isValid = false;
        }
        else {
            $('#r_name+.error').hide();
        }

        var emailReg = /^\w+@\w+\.\w+$/;
        if (!emailReg.test(email)) {
            $('#email+.error').show();
            isValid = false;
        }
        else {
            $('#email+.error').hide();
        }

        var passwordReg = /^[a-z0-9A-Z]{3,6}$/;
        if (!passwordReg.test(password)) {
            $('#r_password~.error').show();
            $('#confirm').val('');
            $('#confirm+.error').hide();
            isValid = false;
        }
        else {
            $('#r_password~.error').hide();
            if ($('#confirm').val() != password) {
                $('#confirm+.error').show();
                isValid = false;
            }
            else {
                $('#confirm+.error').hide();
            }
        }
        /*提交表单*/
        if(isValid) {
            $.post('main/register',
                {
                    name: name,
                    email: email,
                    password: $.md5(name + $.md5(name + password)) //加密
                },
                function (data, status) {
                    if (data != "ok") {
                        $('#r_name~.error:nth-of-type(2)').show();
                    }
                    else {
                        alert("ok");
                    }
            });
        }
    });

    /*密码可见*/
    $('#showOrHide').click(function () {
        if ($('#r_password').hasClass("pswHide")) {
            $('#r_password, #confirm').attr('type', 'text');
            $('#r_password').removeClass("pswHide");
            $('#showOrHide').text("隐藏密码");
        }
        else {
            $('#r_password, #confirm').attr('type', 'password');
            $('#r_password').addClass("pswHide");
            $('#showOrHide').text("显示密码");
        }
    });
});