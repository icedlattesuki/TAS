/*================================================================================
  Item Name: Materialize - Material Design Admin Template
  Version: 4.0
  Author: PIXINVENT
  Author URL: https://themeforest.net/user/pixinvent/portfolio
================================================================================*/

  /*Preloader*/
  $(window).on('load', function() {
    setTimeout(function() {
      $('body').addClass('loaded');
    }, 200);
  });

  $(function() {

    "use strict";

    var window_width = $(window).width();
    var openIndex;

    // Collapsible navigation menu
    $('.nav-collapsible .navbar-toggler').click(function() {
      //set Index velue
      getCollapseIndex();
      // Toggle navigation expan and collapse on radio click
      if ($('#left-sidebar-nav').hasClass('nav-expanded') && !$('#left-sidebar-nav').hasClass('nav-lock')) {
        $('#left-sidebar-nav').toggleClass('nav-expanded');
        $('#main').toggleClass('main-full');
      } else {
        $('#left-sidebar-nav').toggleClass('nav-expanded nav-collapsed');
        $('#main').toggleClass('main-full');
      }
      // Set navigation lock / unlock with radio icon
      if ($(this).children().text() == 'radio_button_unchecked') {
        $(this).children().text('radio_button_checked');
        $('#left-sidebar-nav').addClass('nav-lock');
        $('.header-search-wrapper').addClass('sideNav-lock');
      } else {
        $(this).children().text('radio_button_unchecked');
        $('#left-sidebar-nav').removeClass('nav-lock');
        $('.header-search-wrapper').removeClass('sideNav-lock');
      }

      setTimeout(function() {
        if (openIndex != null) {
          if ($('#left-sidebar-nav').hasClass('nav-collapsed')) {
            $('.collapsible').collapsible('close', (openIndex));
          }
        }
      }, 100);
    });

    // Expand navigation on mouseenter event
    $('#left-sidebar-nav.nav-collapsible').mouseenter(function() {
      //set Index velue
      getCollapseIndex();

      if (!$(this).hasClass('nav-lock')) {
        $(this).addClass('nav-expanded').removeClass('nav-collapsed');
        setTimeout(function() {
          $('.collapsible').collapsible('open', (openIndex));
        }, 100);
      }
    });

    // Collapse navigation on mouseleave event
    $('#left-sidebar-nav.nav-collapsible').mouseleave(function() {
      //set Index velue
      getCollapseIndex();
      if (!$(this).hasClass('nav-lock')) {
        $(this).addClass('nav-collapsed').removeClass('nav-expanded');
        setTimeout(function() {
          $('.collapsible').collapsible('close', (openIndex));
        }, 100);
      }
    });

    function getCollapseIndex() {
      $("#slide-out > li > ul > li > a.collapsible-header").each(function(index) {
        if ($(this).parent().hasClass('active')) {
          openIndex = index;
        }
      });
    }

    // Search class for focus
    $('.header-search-input').focus(
      function() {
        $(this).parent('div').addClass('header-search-wrapper-focus');
      }).blur(
      function() {
        $(this).parent('div').removeClass('header-search-wrapper-focus');
      });

    // Check first if any of the task is checked
    $('#task-card input:checkbox').each(function() {
      checkbox_check(this);
    });

    // Task check box
    $('#task-card input:checkbox').change(function() {
      checkbox_check(this);
    });

    // Check Uncheck function
    function checkbox_check(el) {
      if (!$(el).is(':checked')) {
        $(el).next().css('text-decoration', 'none'); // or addClass
      } else {
        $(el).next().css('text-decoration', 'line-through'); //or addClass
      }
    }

    // Swipeable Tabs Demo Init
    if ($('#tabs-swipe-demo').length) {
      $('#tabs-swipe-demo').tabs({
        'swipeable': true
      });
    }

    // Plugin initialization

    $('select').material_select();
    // Set checkbox on forms.html to indeterminate
    var indeterminateCheckbox = document.getElementById('indeterminate-checkbox');
    if (indeterminateCheckbox !== null)
      indeterminateCheckbox.indeterminate = true;

    // Materialize Slider
    $('.slider').slider({
      full_width: true
    });

    // Commom, Translation & Horizontal Dropdown
    $('.dropdown-button, .translation-button, .dropdown-menu').dropdown({
      inDuration: 300,
      outDuration: 225,
      constrainWidth: false,
      hover: true,
      gutter: 0,
      belowOrigin: true,
      alignment: 'left',
      stopPropagation: false
    });
    // Notification, Profile & Settings Dropdown
    $('.notification-button, .profile-button, .dropdown-settings').dropdown({
      inDuration: 300,
      outDuration: 225,
      constrainWidth: false,
      hover: true,
      gutter: 0,
      belowOrigin: true,
      alignment: 'right',
      stopPropagation: false
    });

    // Materialize Tabs
    $('.tab-demo').show().tabs();
    $('.tab-demo-active').show().tabs();

    // Materialize Parallax
    $('.parallax').parallax();

    // Materialize scrollSpy
    $('.scrollspy').scrollSpy();

    // Materialize tooltip
    $('.tooltipped').tooltip({
      delay: 50
    });

    //Main Left Sidebar Menu
    $('.sidebar-collapse').sideNav({
      edge: 'left', // Choose the horizontal origin
    });

    // Overlay Menu (Full screen menu)
    $('.menu-sidebar-collapse').sideNav({
      menuWidth: 240,
      edge: 'left', // Choose the horizontal origin
      //closeOnClick:true, // Set if default menu open is true
      menuOut: false // Set if default menu open is true
    });

    //Main Left Sidebar Chat
    $('.chat-collapse').sideNav({
      menuWidth: 300,
      edge: 'right',
    });

    // Pikadate datepicker
    $('.datepicker').pickadate({
      selectMonths: true, // Creates a dropdown to control month
      selectYears: 15 // Creates a dropdown of 15 years to control year
    });

    // Perfect Scrollbar
    $('select').not('.disabled').material_select();
    var leftnav = $(".page-topbar").height();
    var leftnavHeight = window.innerHeight - leftnav;
    if (!$('#slide-out.leftside-navigation').hasClass('native-scroll')) {
      $('.leftside-navigation').perfectScrollbar({
        suppressScrollX: true
      });
    }
    var righttnav = $("#chat-out").height();
    $('.rightside-navigation').perfectScrollbar({
      suppressScrollX: true
    });

    // Fullscreen
    function toggleFullScreen() {
      if ((document.fullScreenElement && document.fullScreenElement !== null) ||
        (!document.mozFullScreen && !document.webkitIsFullScreen)) {
        if (document.documentElement.requestFullScreen) {
          document.documentElement.requestFullScreen();
        } else if (document.documentElement.mozRequestFullScreen) {
          document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullScreen) {
          document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
        }
      } else {
        if (document.cancelFullScreen) {
          document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        }
      }
    }

    $('.toggle-fullscreen').click(function() {
      toggleFullScreen();
    });


    // Floating-Fixed table of contents (Materialize pushpin)
    if ($('nav').length) {
      $('.toc-wrapper').pushpin({
        top: $('nav').height()
      });
    } else if ($('#index-banner').length) {
      $('.toc-wrapper').pushpin({
        top: $('#index-banner').height()
      });
    } else {
      $('.toc-wrapper').pushpin({
        top: 0
      });
    }

    // Toggle Flow Text
    var toggleFlowTextButton = $('#flow-toggle')
    toggleFlowTextButton.click(function() {
      $('#flow-text-demo').children('p').each(function() {
        $(this).toggleClass('flow-text');
      })
    });

    //Alerts
    $("#card-alert .close").click(function() {
      $(this).closest('#card-alert').fadeOut('slow');
    });

    //Toggle Containers on page
    var toggleContainersButton = $('#container-toggle-button');
    toggleContainersButton.click(function() {
      $('body .browser-window .container, .had-container').each(function() {
        $(this).toggleClass('had-container');
        $(this).toggleClass('container');
        if ($(this).hasClass('container')) {
          toggleContainersButton.text("Turn off Containers");
        } else {
          toggleContainersButton.text("Turn on Containers");
        }
      });
    });

    // Detect touch screen and enable scrollbar if necessary
    function is_touch_device() {
      try {
        document.createEvent("TouchEvent");
        return true;
      } catch (e) {
        return false;
      }
    }
    if (is_touch_device()) {
      $('#nav-mobile').css({
        overflow: 'auto'
      })
    }
  });