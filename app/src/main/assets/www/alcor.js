$( document ).ready(function() {

    $("#alcor_svg").height($("#alcor_svg").width() * 530 / 768);

	// set variables for every star
    var starOne = $(document.getElementById("starOne"));
    var starTwo = $(document.getElementById("starTwo"));
    var starThree = $(document.getElementById("starThree"));
    var starFour = $(document.getElementById("starFour"));
    var starFive = $(document.getElementById("starFive"));
    var starSix = $(document.getElementById("starSix"));
    var starSeven = $(document.getElementById("starSeven"));
    var starEight = $(document.getElementById("starEight"));

    // bind each star to touch
	starOne.bind( "touchstart", touchStarOne );
	starTwo.bind( "touchstart", touchStarTwo );
	starThree.bind( "touchstart", touchStarThree );
	starFour.bind( "touchstart", touchStarFour );
	starFive.bind( "touchstart", touchStarFive );
	starSix.bind( "touchstart", touchStarSix );
	starSeven.bind( "touchstart", touchStarSeven );
	starEight.bind( "touchstart", touchStarEight );
 
	function touchStarOne( event ){

		console.log("touch starOne");

        // stop animation on star
        starOne.css({
          "-webkit-animation-play-state": "paused"
        });

        	// dim letters not present in  name of star
        $(".alcor_1").animate({opacity: 0},2000);
        $(".alcor_3").animate({opacity: 0},2000);
        $(".alcor_4").animate({opacity: 0},2000);
        $(".alcor_5").animate({opacity: 0},2000);
        $(".alcor_6").animate({opacity: 0},2000);
        $(".alcor_7").animate({opacity: 0},2000);
        $(".alcor_8").animate({opacity: 0},2000);
        $(".alcor_9").animate({opacity: 0},2000);
        $(".alcor_10").animate({opacity: 0},2000);
        $(".alcor_12").animate({opacity: 0},2000);
        $(".alcor_13").animate({opacity: 0},2000);
        $(".alcor_14").animate({opacity: 0},2000);
        $(".alcor_16").animate({opacity: 0},2000);
        $(".alcor_17").animate({opacity: 0},2000);
        $(".alcor_18").animate({opacity: 0},2000);
        $(".alcor_19").animate({opacity: 0},2000);
        $(".alcor_20").animate({opacity: 0},2000);
        $(".alcor_21").animate({opacity: 0},2000);
        $(".alcor_22").animate({opacity: 0},2000);
        $(".alcor_24").animate({opacity: 0},2000);
        $(".alcor_25").animate({opacity: 0},2000);
        $(".alcor_26").animate({opacity: 0},2000);
        $(".alcor_27").animate({opacity: 0},2000);
        $(".alcor_28").animate({opacity: 0},2000);

        $(".alcor_1").animate({opacity: 1},3000);
        $(".alcor_3").animate({opacity: 1},3000);
        $(".alcor_4").animate({opacity: 1},3000);
        $(".alcor_5").animate({opacity: 1},3000);
        $(".alcor_6").animate({opacity: 1},3000);
        $(".alcor_7").animate({opacity: 1},3000);
        $(".alcor_8").animate({opacity: 1},3000);
        $(".alcor_9").animate({opacity: 1},3000);
        $(".alcor_10").animate({opacity: 1},3000);
        $(".alcor_12").animate({opacity: 1},3000);
        $(".alcor_13").animate({opacity: 1},3000);
        $(".alcor_14").animate({opacity: 1},3000);
        $(".alcor_16").animate({opacity: 1},3000);
        $(".alcor_17").animate({opacity: 1},3000);
        $(".alcor_18").animate({opacity: 1},3000);
        $(".alcor_19").animate({opacity: 1},3000);
        $(".alcor_20").animate({opacity: 1},3000);
        $(".alcor_21").animate({opacity: 1},3000);
        $(".alcor_22").animate({opacity: 1},3000);
        $(".alcor_24").animate({opacity: 1},3000);
        $(".alcor_25").animate({opacity: 1},3000);
        $(".alcor_26").animate({opacity: 1},3000);
        $(".alcor_27").animate({opacity: 1},3000);
        $(".alcor_28").animate({opacity: 1},3000);
        		
        // launch animation for next star
        setTimeout (function(){
          starTwo.css({
            "-webkit-animation-play-state": "running"
          });
        },2000);
  	
    }

	function touchStarTwo( event ) {

        console.log("touch starTwo");

        // stop animation on star
        starTwo.css({
          "-webkit-animation-play-state": "paused"
        });

        // dim letters not present in  name of star

        $(".alcor_1").animate({opacity: 0},2000);
        $(".alcor_2").animate({opacity: 0},2000);
        $(".alcor_3").animate({opacity: 0},2000);
        $(".alcor_4").animate({opacity: 0},2000);
        $(".alcor_5").animate({opacity: 0},2000);
        $(".alcor_6").animate({opacity: 0},2000);
        $(".alcor_7").animate({opacity: 0},2000);
        $(".alcor_10").animate({opacity: 0},2000);
        $(".alcor_11").animate({opacity: 0},2000);
        $(".alcor_13").animate({opacity: 0},2000);
        $(".alcor_14").animate({opacity: 0},2000);
        $(".alcor_15").animate({opacity: 0},2000);
        $(".alcor_17").animate({opacity: 0},2000);
        $(".alcor_18").animate({opacity: 0},2000);
        $(".alcor_19").animate({opacity: 0},2000);
        $(".alcor_20").animate({opacity: 0},2000);
        $(".alcor_21").animate({opacity: 0},2000);
        $(".alcor_22").animate({opacity: 0},2000);
        $(".alcor_23").animate({opacity: 0},2000);
        $(".alcor_24").animate({opacity: 0},2000);
        $(".alcor_25").animate({opacity: 0},2000);
        $(".alcor_26").animate({opacity: 0},2000);
        $(".alcor_27").animate({opacity: 0},2000);
        $(".alcor_28").animate({opacity: 0},2000);

        $(".alcor_1").animate({opacity: 1},3000);
        $(".alcor_2").animate({opacity: 1},3000);
        $(".alcor_3").animate({opacity: 1},3000);
        $(".alcor_4").animate({opacity: 1},3000);
        $(".alcor_5").animate({opacity: 1},3000);
        $(".alcor_6").animate({opacity: 1},3000);
        $(".alcor_7").animate({opacity: 1},3000);
        $(".alcor_10").animate({opacity: 1},3000);
        $(".alcor_11").animate({opacity: 1},3000);
        $(".alcor_13").animate({opacity: 1},3000);
        $(".alcor_14").animate({opacity: 1},3000);
        $(".alcor_15").animate({opacity: 1},3000);
        $(".alcor_17").animate({opacity: 1},3000);
        $(".alcor_18").animate({opacity: 1},3000);
        $(".alcor_19").animate({opacity: 1},3000);
        $(".alcor_20").animate({opacity: 1},3000);
        $(".alcor_21").animate({opacity: 1},3000);
        $(".alcor_22").animate({opacity: 1},3000);
        $(".alcor_23").animate({opacity: 1},3000);
        $(".alcor_24").animate({opacity: 1},3000);
        $(".alcor_25").animate({opacity: 1},3000);
        $(".alcor_26").animate({opacity: 1},3000);
        $(".alcor_27").animate({opacity: 1},3000);
        $(".alcor_28").animate({opacity: 1},3000);

        // launch animation for next star
        setTimeout (function(){
          starThree.css({
            "-webkit-animation-play-state": "running"
          });
        },2000);

    };

    function touchStarThree( event ) {

        console.log("touch starThree");

        // stop animation on star
        starThree.css({
          "-webkit-animation-play-state": "paused"
        });

        // dim letters not present in  name of star

        $(".alcor_1").animate({opacity: 0},2000);
        $(".alcor_2").animate({opacity: 0},2000);
        $(".alcor_3").animate({opacity: 0},2000);
        $(".alcor_4").animate({opacity: 0},2000);
        $(".alcor_6").animate({opacity: 0},2000);
        $(".alcor_9").animate({opacity: 0},2000);
        $(".alcor_11").animate({opacity: 0},2000);
        $(".alcor_12").animate({opacity: 0},2000);
        $(".alcor_13").animate({opacity: 0},2000);
        $(".alcor_14").animate({opacity: 0},2000);
        $(".alcor_15").animate({opacity: 0},2000);
        $(".alcor_17").animate({opacity: 0},2000);
        $(".alcor_18").animate({opacity: 0},2000);
        $(".alcor_19").animate({opacity: 0},2000);
        $(".alcor_20").animate({opacity: 0},2000);
        $(".alcor_21").animate({opacity: 0},2000);
        $(".alcor_22").animate({opacity: 0},2000);
        $(".alcor_23").animate({opacity: 0},2000);
        $(".alcor_24").animate({opacity: 0},2000);
        $(".alcor_25").animate({opacity: 0},2000);
        $(".alcor_26").animate({opacity: 0},2000);
        $(".alcor_27").animate({opacity: 0},2000);
        $(".alcor_28").animate({opacity: 0},2000);
        $(".alcor_1").animate({opacity: 1},3000);
        $(".alcor_2").animate({opacity: 1},3000);
        $(".alcor_3").animate({opacity: 1},3000);
        $(".alcor_4").animate({opacity: 1},3000);
        $(".alcor_6").animate({opacity: 1},3000);
        $(".alcor_9").animate({opacity: 1},3000);
        $(".alcor_11").animate({opacity: 1},3000);
        $(".alcor_12").animate({opacity: 1},3000);
        $(".alcor_13").animate({opacity: 1},3000);
        $(".alcor_14").animate({opacity: 1},3000);
        $(".alcor_15").animate({opacity: 1},3000);
        $(".alcor_17").animate({opacity: 1},3000);
        $(".alcor_18").animate({opacity: 1},3000);
        $(".alcor_19").animate({opacity: 1},3000);
        $(".alcor_20").animate({opacity: 1},3000);
        $(".alcor_21").animate({opacity: 1},3000);
        $(".alcor_22").animate({opacity: 1},3000);
        $(".alcor_23").animate({opacity: 1},3000);
        $(".alcor_24").animate({opacity: 1},3000);
        $(".alcor_25").animate({opacity: 1},3000);
        $(".alcor_26").animate({opacity: 1},3000);
        $(".alcor_27").animate({opacity: 1},3000);
        $(".alcor_28").animate({opacity: 1},3000);

        // launch animation for next star
        setTimeout (function(){
          starFour.css({
            "-webkit-animation-play-state": "running"
          });
        },2000);

    };

	function touchStarFour( event ) {
    
        console.log("touch starFour");

        // stop animation on star
        starFour.css({
          "-webkit-animation-play-state": "paused"
        });

        // dim letters not present in  name of star

        $(".alcor_1").animate({opacity: 0},2000);
        $(".alcor_2").animate({opacity: 0},2000);
        $(".alcor_3").animate({opacity: 0},2000);
        $(".alcor_4").animate({opacity: 0},2000);
        $(".alcor_5").animate({opacity: 0},2000);
        $(".alcor_6").animate({opacity: 0},2000);
        $(".alcor_7").animate({opacity: 0},2000);
        $(".alcor_8").animate({opacity: 0},2000);
        $(".alcor_10").animate({opacity: 0},2000);
        $(".alcor_11").animate({opacity: 0},2000);
        $(".alcor_13").animate({opacity: 0},2000);
        $(".alcor_15").animate({opacity: 0},2000);
        $(".alcor_16").animate({opacity: 0},2000);
        $(".alcor_17").animate({opacity: 0},2000);
        $(".alcor_18").animate({opacity: 0},2000);
        $(".alcor_19").animate({opacity: 0},2000);
        $(".alcor_20").animate({opacity: 0},2000);
        $(".alcor_21").animate({opacity: 0},2000);
        $(".alcor_22").animate({opacity: 0},2000);
        $(".alcor_23").animate({opacity: 0},2000);
        $(".alcor_25").animate({opacity: 0},2000);
        $(".alcor_26").animate({opacity: 0},2000);
        $(".alcor_27").animate({opacity: 0},2000);
        $(".alcor_28").animate({opacity: 0},2000);

        $(".alcor_1").animate({opacity: 1},3000);
        $(".alcor_2").animate({opacity: 1},3000);
        $(".alcor_3").animate({opacity: 1},3000);
        $(".alcor_4").animate({opacity: 1},3000);
        $(".alcor_5").animate({opacity: 1},3000);
        $(".alcor_6").animate({opacity: 1},3000);
        $(".alcor_7").animate({opacity: 1},3000);
        $(".alcor_8").animate({opacity: 1},3000);
        $(".alcor_10").animate({opacity: 1},3000);
        $(".alcor_11").animate({opacity: 1},3000);
        $(".alcor_13").animate({opacity: 1},3000);
        $(".alcor_15").animate({opacity: 1},3000);
        $(".alcor_16").animate({opacity: 1},3000);
        $(".alcor_17").animate({opacity: 1},3000);
        $(".alcor_18").animate({opacity: 1},3000);
        $(".alcor_19").animate({opacity: 1},3000);
        $(".alcor_20").animate({opacity: 1},3000);
        $(".alcor_21").animate({opacity: 1},3000);
        $(".alcor_22").animate({opacity: 1},3000);
        $(".alcor_23").animate({opacity: 1},3000);
        $(".alcor_25").animate({opacity: 1},3000);
        $(".alcor_26").animate({opacity: 1},3000);
        $(".alcor_27").animate({opacity: 1},3000);
        $(".alcor_28").animate({opacity: 1},3000);

        // launch animation for next star
        setTimeout (function(){
          starFive.css({
            "-webkit-animation-play-state": "running"
          });
        },2000);

    };
	
    function touchStarFive( event ) {

        console.log("touch starFive");

        // stop animation on star
        starFive.css({
          "-webkit-animation-play-state": "paused"
        });

        // dim letters not present in  name of star

        $(".alcor_2").animate({opacity: 0},2000);
        $(".alcor_4").animate({opacity: 0},2000);
        $(".alcor_5").animate({opacity: 0},2000);
        $(".alcor_7").animate({opacity: 0},2000);
        $(".alcor_8").animate({opacity: 0},2000);
        $(".alcor_9").animate({opacity: 0},2000);
        $(".alcor_10").animate({opacity: 0},2000);
        $(".alcor_11").animate({opacity: 0},2000);
        $(".alcor_12").animate({opacity: 0},2000);
        $(".alcor_13").animate({opacity: 0},2000);
        $(".alcor_14").animate({opacity: 0},2000);
        $(".alcor_15").animate({opacity: 0},2000);
        $(".alcor_16").animate({opacity: 0},2000);
        $(".alcor_19").animate({opacity: 0},2000);
        $(".alcor_20").animate({opacity: 0},2000);
        $(".alcor_21").animate({opacity: 0},2000);
        $(".alcor_22").animate({opacity: 0},2000);
        $(".alcor_23").animate({opacity: 0},2000);
        $(".alcor_24").animate({opacity: 0},2000);
        $(".alcor_25").animate({opacity: 0},2000);
        $(".alcor_26").animate({opacity: 0},2000);
        $(".alcor_27").animate({opacity: 0},2000);
        $(".alcor_28").animate({opacity: 0},2000);
        $(".alcor_2").animate({opacity: 1},3000);
        $(".alcor_4").animate({opacity: 1},3000);
        $(".alcor_5").animate({opacity: 1},3000);
        $(".alcor_7").animate({opacity: 1},3000);
        $(".alcor_8").animate({opacity: 1},3000);
        $(".alcor_9").animate({opacity: 1},3000);
        $(".alcor_10").animate({opacity: 1},3000);
        $(".alcor_11").animate({opacity: 1},3000);
        $(".alcor_12").animate({opacity: 1},3000);
        $(".alcor_13").animate({opacity: 1},3000);
        $(".alcor_14").animate({opacity: 1},3000);
        $(".alcor_15").animate({opacity: 1},3000);
        $(".alcor_16").animate({opacity: 1},3000);
        $(".alcor_19").animate({opacity: 1},3000);
        $(".alcor_20").animate({opacity: 1},3000);
        $(".alcor_21").animate({opacity: 1},3000);
        $(".alcor_22").animate({opacity: 1},3000);
        $(".alcor_23").animate({opacity: 1},3000);
        $(".alcor_24").animate({opacity: 1},3000);
        $(".alcor_25").animate({opacity: 1},3000);
        $(".alcor_26").animate({opacity: 1},3000);
        $(".alcor_27").animate({opacity: 1},3000);
        $(".alcor_28").animate({opacity: 1},3000);

        // launch animation for star Two
        setTimeout (function(){
          starSix.css({
            "-webkit-animation-play-state": "running"
          });
          starSeven.css({
            "-webkit-animation-play-state": "running"
          });
        },2000);

    };
	
    function touchStarSix( event ) {
        console.log("touch starSix");

        // stop animation on star
        starSix.css({
          "-webkit-animation-play-state": "paused"
        });

        // dim letters not present in  name of star

        $(".alcor_1").animate({opacity: 0},2000);
        $(".alcor_2").animate({opacity: 0},2000);
        $(".alcor_3").animate({opacity: 0},2000);
        $(".alcor_4").animate({opacity: 0},2000);
        $(".alcor_5").animate({opacity: 0},2000);
        $(".alcor_7").animate({opacity: 0},2000);
        $(".alcor_8").animate({opacity: 0},2000);
        $(".alcor_10").animate({opacity: 0},2000);
        $(".alcor_11").animate({opacity: 0},2000);
        $(".alcor_12").animate({opacity: 0},2000);
        $(".alcor_13").animate({opacity: 0},2000);
        $(".alcor_14").animate({opacity: 0},2000);
        $(".alcor_15").animate({opacity: 0},2000);
        $(".alcor_17").animate({opacity: 0},2000);
        $(".alcor_18").animate({opacity: 0},2000);
        $(".alcor_19").animate({opacity: 0},2000);
        $(".alcor_20").animate({opacity: 0},2000);
        $(".alcor_21").animate({opacity: 0},2000);
        $(".alcor_23").animate({opacity: 0},2000);
        $(".alcor_24").animate({opacity: 0},2000);
        $(".alcor_25").animate({opacity: 0},2000);
        $(".alcor_26").animate({opacity: 0},2000);
        $(".alcor_27").animate({opacity: 0},2000);
        $(".alcor_28").animate({opacity: 0},2000);

        // launch animation for star Two
        setTimeout (function(){
          starSeven.css({
            "-webkit-animation-play-state": "running"
          });
        },2000);

    };

    function touchStarSeven( event ) {

        console.log("touch starSeven");
        // stop animation on star



        // dim letters not present in  name of star
        $(".alcor_2").animate({opacity: 0},4000);
        $(".alcor_4").animate({opacity: 0},4000);
        $(".alcor_5").animate({opacity: 0},4000);
        $(".alcor_6").animate({opacity: 0},4000);
        $(".alcor_7").animate({opacity: 0},4000);
        $(".alcor_8").animate({opacity: 0},4000);
        $(".alcor_9").animate({opacity: 0},4000);
        $(".alcor_11").animate({opacity: 0},4000);
        $(".alcor_12").animate({opacity: 0},4000);
        $(".alcor_13").animate({opacity: 0},4000);
        $(".alcor_14").animate({opacity: 0},4000);
        $(".alcor_15").animate({opacity: 0},4000);
        $(".alcor_16").animate({opacity: 0},4000);
        $(".alcor_17").animate({opacity: 0},4000);
        $(".alcor_19").animate({opacity: 0},4000);
        $(".alcor_20").animate({opacity: 0},4000);
        $(".alcor_21").animate({opacity: 0},4000);
        $(".alcor_23").animate({opacity: 0},4000);
        $(".alcor_24").animate({opacity: 0},4000);
        $(".alcor_25").animate({opacity: 0},4000);
        $(".alcor_26").animate({opacity: 0},4000);
        $(".alcor_27").animate({opacity: 0},4000);
        $(".alcor_28").animate({opacity: 0},4000);

        $(".alcor_2").animate({opacity: 1},4000);
        $(".alcor_4").animate({opacity: 1},4000);
        $(".alcor_5").animate({opacity: 1},4000);
        $(".alcor_6").animate({opacity: 1},4000);
        $(".alcor_7").animate({opacity: 1},4000);
        $(".alcor_8").animate({opacity: 1},4000);
        $(".alcor_9").animate({opacity: 1},4000);
        $(".alcor_11").animate({opacity: 1},4000);
        $(".alcor_12").animate({opacity: 1},4000);
        $(".alcor_13").animate({opacity: 1},4000);
        $(".alcor_14").animate({opacity: 1},4000);
        $(".alcor_15").animate({opacity: 1},4000);
        $(".alcor_16").animate({opacity: 1},4000);
        $(".alcor_17").animate({opacity: 1},4000);
        $(".alcor_19").animate({opacity: 1},4000);
        $(".alcor_20").animate({opacity: 1},4000);
        $(".alcor_21").animate({opacity: 1},4000);
        $(".alcor_23").animate({opacity: 1},4000);
        $(".alcor_24").animate({opacity: 1},4000);
        $(".alcor_25").animate({opacity: 1},4000);
        $(".alcor_26").animate({opacity: 1},4000);
        $(".alcor_27").animate({opacity: 1},4000);
        $(".alcor_28").animate({opacity: 1},4000);

        $('.star_txt').css({
            "visibility": "hidden"
        });
        $('.abbey_txt').css({
            "visibility": "visible"
        });
        

    };

    function touchStarEight( event ) {
        console.log("touch starEight");

        // stop animation on star
        starEight.css({
          "-webkit-animation-play-state": "paused"
        });

        // dim letters not present in  name of star

        $(".alcor_2").animate({opacity: 0},2000);
        $(".alcor_4").animate({opacity: 0},2000);
        $(".alcor_5").animate({opacity: 0},2000);
        $(".alcor_6").animate({opacity: 0},2000);
        $(".alcor_8").animate({opacity: 0},2000);
        $(".alcor_9").animate({opacity: 0},2000);
        $(".alcor_10").animate({opacity: 0},2000);
        $(".alcor_11").animate({opacity: 0},2000);
        $(".alcor_12").animate({opacity: 0},2000);
        $(".alcor_13").animate({opacity: 0},2000);
        $(".alcor_14").animate({opacity: 0},2000);
        $(".alcor_15").animate({opacity: 0},2000);
        $(".alcor_17").animate({opacity: 0},2000);
        $(".alcor_18").animate({opacity: 0},2000);
        $(".alcor_19").animate({opacity: 0},2000);
        $(".alcor_21").animate({opacity: 0},2000);
        $(".alcor_22").animate({opacity: 0},2000);
        $(".alcor_23").animate({opacity: 0},2000);
        $(".alcor_24").animate({opacity: 0},2000);
        $(".alcor_25").animate({opacity: 0},2000);
        $(".alcor_26").animate({opacity: 0},2000);
        $(".alcor_27").animate({opacity: 0},2000);
        $(".alcor_28").animate({opacity: 0},2000);
        $(".alcor_2").animate({opacity: 1},3000);
        $(".alcor_4").animate({opacity: 1},3000);
        $(".alcor_5").animate({opacity: 1},3000);
        $(".alcor_6").animate({opacity: 1},3000);
        $(".alcor_8").animate({opacity: 1},3000);
        $(".alcor_9").animate({opacity: 1},3000);
        $(".alcor_10").animate({opacity: 1},3000);
        $(".alcor_11").animate({opacity: 1},3000);
        $(".alcor_12").animate({opacity: 1},3000);
        $(".alcor_13").animate({opacity: 1},3000);
        $(".alcor_14").animate({opacity: 1},3000);
        $(".alcor_15").animate({opacity: 1},3000);
        $(".alcor_17").animate({opacity: 1},3000);
        $(".alcor_18").animate({opacity: 1},3000);
        $(".alcor_19").animate({opacity: 1},3000);
        $(".alcor_21").animate({opacity: 1},3000);
        $(".alcor_22").animate({opacity: 1},3000);
        $(".alcor_23").animate({opacity: 1},3000);
        $(".alcor_24").animate({opacity: 1},3000);
        $(".alcor_25").animate({opacity: 1},3000);
        $(".alcor_26").animate({opacity: 1},3000);
        $(".alcor_27").animate({opacity: 1},3000);
        $(".alcor_28").animate({opacity: 1},3000);

    };

});