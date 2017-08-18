
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("assets/img/backgrounds/1.jpg");
    
    /*
        Forms show / hide
    */
    $('.show-register-form').on('click', function(){
    	if( ! $(this).hasClass('active') ) {
            $('.r-form input').val("");

            $('.r-form p.error').each(function () {
				$(this).hide();
            });

            $('#r_password').addClass("pswHide");
            $('#r_password').attr('type', 'password');
            $('#showOrHide').text('显示密码');

    		$('.show-login-form').removeClass('active');
    		$(this).addClass('active');
    		$('.login-form').fadeOut('fast', function(){
    			$('.register-form').fadeIn('fast');
    		});
    	}
    });
    // ---
    $('.show-login-form').on('click', function(){
    	if( ! $(this).hasClass('active') ) {
            $('.l-form .error').hide();
            $('.l-form input').val('');

            $('#l_password').addClass("pswHide");
            $('#l_password').attr('type', 'password');
            $('#showOrHide_l').text('显示密码');

    		$('.show-register-form').removeClass('active');
    		$(this).addClass('active');
    		$('.register-form').fadeOut('fast', function(){
    			$('.login-form').fadeIn('fast');
    		});
    	}
    });
    
});
