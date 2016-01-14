//*******************  UI  *******************//
			jq(function(){

				// Accordion
				jq("#accordion").accordion({ header: "h3" });

				// Tabs
				jq('#tabs').tabs();

				// Dialog
				jq('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Ok": function() {
							jq(this).dialog("close");
						},
						"Cancel": function() {
							jq(this).dialog("close");
						}
					}
				});

				// Dialog Link
				jq('#dialog_link').click(function(){
					jq('#dialog').dialog('open');
					return false;
				});

				// Datepicker
				jq('#datepicker').datepicker({
					inline: true
				});
				jq('#inline-datepicker').datepicker({
					inline: true
				});

				// Slider
				jq( "#slider" ).slider(
					{
						slide: function( event, ui ) {
							jq( "#amount" ).val( "jq" + ui.value );
						}
					}
				);

				jq( "#slider2" ).slider({
						value:100,
						min: 0,
						max: 500,
						step: 1,
						slide: function( event, ui ) {
							jq( "#amount" ).val( "jq" + ui.value );
						}
					});
				jq( "#amount" ).val( "jq" + jq( "#slider" ).slider( "value" ) );
				jq( "#slider-range" ).slider({
					range: true,
					min: 0,
					max: 500,
					values: [ 75, 300 ],
					slide: function( event, ui ) {
						jq( "#amount2" ).val( "jq" + ui.values[ 0 ] + " - jq" + ui.values[ 1 ] );
					}
				});
				jq( "#amount2" ).val( "jq" + jq( "#slider-range" ).slider( "values", 0 ) +
					" - jq" + jq( "#slider-range" ).slider( "values", 1 ) );
					// setup graphic EQ
				jq( "#eq > span" ).each(function() {
					// read initial values from markup and remove that
					var value = parseInt( jq( this ).text(), 10 );
					jq( this ).empty().slider({
						value: value,
						range: "min",
						animate: true,
						orientation: "vertical"
					});
				});
				jq( "#slider-range-min" ).slider({
					range: "min",
					value: 23,
					min: 23,
					max: 500,
					slide: function( event, ui ) {
						jq( "#amount3" ).val( "jq" + ui.value );
					}
				});
				jq( "#amount3" ).val( "jq" + jq( "#slider-range-min" ).slider( "value" ) );
				jq( "#slider-range-max" ).slider({
					range: "max",
					value: 56,
					min: 1,
					max: 350,
					slide: function( event, ui ) {
						jq( "#amount4" ).val( "jq" + ui.value );
					}
				});
				jq( "#amount4" ).val( "jq" + jq( "#slider-range-min" ).slider( "value" ) );
				// Progressbar
				jq("#progressbar").progressbar({
					value: 20
				});

				//hover states on the static widgets
				jq('#dialog_link, ul#icons li').hover(
					function() { jq(this).addClass('ui-state-hover'); },
					function() { jq(this).removeClass('ui-state-hover'); }
				);

			});


//*******************  MENU LEFT  *******************//
jQuery.fn.initMenu = function() {
    return this.each(function(){
        var theMenu = jq(this).get(0);
        jq('.acitem', this).hide();
        jq('li.expand > .acitem', this).show();
        jq('li.expand > .acitem', this).prev().addClass('active');
        jq('li a', this).click(
            function(e) {
                e.stopImmediatePropagation();
                var theElement = jq(this).next();
                var parent = this.parentNode.parentNode;
                if(jq(parent).hasClass('noaccordion')) {
                    if(theElement[0] === undefined) {
                        window.location.href = this.href;
                    }
                    jq(theElement).slideToggle('normal', function() {
                        if (jq(this).is(':visible')) {
                            jq(this).prev().addClass('active');
                        }
                        else {
                            jq(this).prev().removeClass('active');
                        }
                    });
                    return false;
                }
                else {
                    if(theElement.hasClass('acitem') && theElement.is(':visible')) {
                        if(jq(parent).hasClass('collapsible')) {
                            jq('.acitem:visible', parent).first().slideUp('normal',
                            function() {
                                jq(this).prev().removeClass('active');
                            }
                        );
                        return false;
                    }
                    return false;
                }
                if(theElement.hasClass('acitem') && !theElement.is(':visible')) {
                    jq('.acitem:visible', parent).first().slideUp('normal', function() {
                        jq(this).prev().removeClass('active');
                    });
                    theElement.slideDown('normal', function() {
                        jq(this).prev().addClass('active');
                    });
                    return false;
                }
            }
        }
    );
});
};

jq(document).ready(function() {jq('.menu').initMenu();});

//*******************  MENU HEADER  *******************//
	jq(document).ready(function(){
				jq('#login-trigger').click(function(){
					jq(this).next('#login-content').slideToggle();
					jq(this).toggleClass('active');

					})
	});



//*******************  Placeholder for all browsers  *******************//

	jq(function() {
	jq("input").each(
		function(){
			if(jq(this).val()=="" && jq(this).attr("placeholder")!=""){
			jq(this).val(jq(this).attr("placeholder"));
			jq(this).focus(function(){
				if(jq(this).val()==jq(this).attr("placeholder")) jq(this).val("");
			});
			jq(this).blur(function(){
				if(jq(this).val()=="") jq(this).val(jq(this).attr("placeholder"));
			});
		}
	});

//*******************  Collapsing blocks jQuery  *******************//

	jq(document).ready(function() {
		jq('.title-grid').append('<span></span>');
		jq('.grid-1 span').each(function() {
			var trigger = jq(this), state = false, el = trigger.parent().next('.content-gird');
			trigger.click(function(){
				state = !state;
				el.slideToggle();
				trigger.parent().parent().toggleClass('inactive');
			});
		});
				jq('.grid-2 span').each(function() {
			var trigger = jq(this), state = false, el = trigger.parent().next('.content-gird');
			trigger.click(function(){
				state = !state;
				el.slideToggle();
				trigger.parent().parent().toggleClass('inactive');
			});
		});
				jq('.grid-3 span').each(function() {
			var trigger = jq(this), state = false, el = trigger.parent().next('.content-gird');
			trigger.click(function(){
				state = !state;
				el.slideToggle();
				trigger.parent().parent().toggleClass('inactive');
			});
		});
	});
				jq('.grid-4 span').each(function() {
			var trigger = jq(this), state = false, el = trigger.parent().next('.content-gird');
			trigger.click(function(){
				state = !state;
				el.slideToggle();
				trigger.parent().parent().toggleClass('inactive');
			});
		});
	});



//*******************  Fancybox  *******************//

	jq(document).ready(function() {
				jq("a.fancybox").fancybox({
				'titlePosition'		: 'outside',
				'overlayColor'		: '#000',
				'overlayOpacity'	: 0.8
			});
	});

//*********************  Information messages   (Alerts)  *********************//
	jq(document).ready(function() {
		jq(".hideit").click(function() {
			jq(this).fadeOut(1000);
		});

	});

//********************* Color Picker  *********************//
	jq(document).ready(function() {
	jq('#colorpickerField1, #colorpickerField2, #colorpickerField3').ColorPicker({
	onSubmit: function(hsb, hex, rgb, el) {
		jq(el).val(hex);
		jq(el).ColorPickerHide();
	},
	onBeforeShow: function () {
		jq(this).ColorPickerSetColor(this.value);
	}
	})
	.bind('keyup', function(){
		jq(this).ColorPickerSetColor(this.value);
	});
	});

//*********************  CALENDAR  *********************//
	jq(document).ready(function() {

		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		var calendar = jq('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('Event Title:');
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect');
			},
			editable: true,
			events: [
				{
					title: 'All Day Event',
					start: new Date(y, m, 1)
				},
				{
					title: 'Long Event',
					start: new Date(y, m, d-5),
					end: new Date(y, m, d-2)
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d-3, 16, 0),
					allDay: false
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+4, 16, 0),
					allDay: false
				},
				{
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false
				},
				{
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false
				},
				{
					title: 'Click for Google',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: 'http://google.com/'
				}
			]
		});

	});

//*********************  File explorer  *********************//
	jq(document).ready(function(){

			var f = jq('#finder').elfinder({
				url : 'lib/elfinder/connectors/php/connector.php',
				lang : 'en',
				docked : true

				// dialog : {
				// 	title : 'File manager',
				// 	height : 500
				// }

				// Callback example
				//editorCallback : function(url) {
				//	if (window.console && window.console.log) {
				//		window.console.log(url);
				//	} else {
				//		alert(url);
				//	}
				//},
				//closeOnEditorCallback : true
			})
			// window.console.log(f)
			jq('#close,#open,#dock,#undock').click(function() {
				jq('#finder').elfinder(jq(this).attr('id'));
			})

		});
//*********************   EDITOR   *********************//
		jq(document).ready(function(){
			jq('#editor').wysiwyg({
				controls:"bold,italic,|,undo,redo,image"
			});
		});


//*********************  FORMS   *********************//
	//select
	jq(document).ready(function() {
	 jq(".chzn-select").chosen(); jq(".chzn-select-deselect").chosen({allow_single_deselect:true});
	});

	jq(document).ready(function(){
	jq("input[type=file]").change(function(){jq(this).parents(".uploader").find(".filename").val(jq(this).val());});
	jq("input[type=file]").each(function(){
	if(jq(this).val()==""){jq(this).parents(".uploader").find(".filename").val("No file selected...");}
	});
	});

//********************* Tooltip *********************//
	jq(function(){

		jq(".tip-top").tipTip({defaultPosition: "top", delay: 1000});
		jq(".tip-bottom").tipTip({defaultPosition: "bottom", delay: 1000});
		jq(".tip-left").tipTip({defaultPosition: "left", delay: 1000});
		jq(".tip-right").tipTip({defaultPosition: "right", delay: 1000});
	});
//*********************   HTML5 Player   *********************//

       jq(function() {

            var playlist = [
                {
                    poster:'lib/player/files/images/sample-playlist-item.png',
                    m4v:'lib/player/files/1.m4v',
                    ogv:'lib/player/files/1.ogv'
                },
                {
                    poster:'lib/player/files/images/sample-playlist-item2.png',
                    m4v:'lib/player/files/1.m4v',
                    ogv:'lib/player/files/1.ogv'
                },
                {
                    poster:'plaer/demo/images/sample-playlist-item.png',
                    m4v:'lib/player/files/1.m4v',
                    ogv:'lib/player/files/1.ogv'
                },
                {
                    poster:'lib/player/files/images/sample-playlist-item2.png',
                    m4v:'lib/player/files/1.m4v',
                    ogv:'lib/player/files/1.ogv'
                }
            ];

            jq('#video-container-inner').ttwVideoPlayer(playlist , {
                debug:true,
                autoHidePlaylist:true,
                jPlayer:{
                    swfPath: "lib/player/jquery-jplayer"
                },
                hdPlaylist:playlist,
                hdButtonCallback:function(){
                    alert('You clicked the hdButton. If specified, the plugin will load an HD version of the currently playing video');
                },
                settingsButtonCallback:function(){
                    alert('You clicked the settingsButton. You can use the settingsButton callback to show a settings screen OR hide this button in the plugin settings');
                }
            });
        });


//********************* Select all Checkbox *********************//
	function setChecked(obj)
		{

		var check = document.getElementsByName("id[]");
		for (var i=0; i<check.length; i++)
		   {
		   check[i].checked = obj.checked;
		   }
	}

//********************* TABLE (NEWS) *********************//
jq(document).ready(function() {
    jq('#example').dataTable( {
        "sPaginationType": "full_numbers"
    } );
} );

//********************* autorisize *********************//

	jq(document).ready(function() {
	jq('textarea.resize-text').autoResize({});
	});

//********************* Contact list *********************//
	 jq(document).ready(function(){
         jq('#slider-contact').sliderNav();
     });


//********************* Auto TAB (Input) *********************//
	jq(document).ready(function() {
		jq('#autotab_example').submit(function() { return false; });
		jq('#autotab_example :input').autotab_magic();
		// Number example
		jq('#area_code, #number1, #number2').autotab_filter('numeric');
		jq('#ssn1, #ssn2, #ssn3').autotab_filter('numeric');
		// Text example
		jq('#text1, #text2, #text3').autotab_filter('text');
		// Alpha example
		jq('#alpha1, #alpha2, #alpha3, #alpha4, #alpha5').autotab_filter('alpha');
		// Alphanumeric example
		jq('#alphanumeric1, #alphanumeric2, #alphanumeric3, #alphanumeric4, #alphanumeric5').autotab_filter({ format: 'alphanumeric', uppercase: true });
		jq('#regex').autotab_filter({ format: 'custom', pattern: '[^0-9\.]' });
	});

//*********************   Charts   *********************//
/* Updating graphs real-time */
jq(function () {
    // we use an inline data source in the example, usually data would
    // be fetched from a server
    var data = [], totalPoints = 300;
    function getRandomData() {
        if (data.length > 0)
            data = data.slice(1);

        // do a random walk
        while (data.length < totalPoints) {
            var prev = data.length > 0 ? data[data.length - 1] : 50;
            var y = prev + Math.random() * 10 - 5;
            if (y < 0)
                y = 0;
            if (y > 100)
                y = 100;
            data.push(y);
        }

        // zip the generated y values with the x values
        var res = [];
        for (var i = 0; i < data.length; ++i)
            res.push([i, data[i]])
        return res;
    }

    // setup control widget
    var updateInterval = 1000;
    jq("#updateInterval").val(updateInterval).change(function () {
        var v = jq(this).val();
        if (v && !isNaN(+v)) {
            updateInterval = +v;
            if (updateInterval < 1)
                updateInterval = 1;
            if (updateInterval > 2000)
                updateInterval = 2000;
            jq(this).val("" + updateInterval);
        }
    });

    // setup plot
    var options = {
        series: { shadowSize: 0 }, // drawing is faster without shadows
        yaxis: { min: 0, max: 120 },
        xaxis: { show: false },

   colors: ["#258dde"],
			series: {
					   lines: {
							lineWidth: 1,
							fill: true,
							fillColor: { colors: [ { opacity: 0.5 }, { opacity: 1.0 } ] },
							steps: false

						}
				   }
		};
    var plot = jq.plot(jq(".autoUpdate"), [ getRandomData() ], options);

    function update() {
        plot.setData([ getRandomData() ]);
        // since the axes don't change, we don't need to call plot.setupGrid()
        plot.draw();

        setTimeout(update, updateInterval);
    }

    update();
});

//*Interacting with the data points *//
jq(function () {
    var sin = [], cos = [];
    for (var i = 0; i < 14; i += 0.5) {
        sin.push([i, Math.sin(i)]);
        cos.push([i, Math.cos(i)]);
    }

    var plot = jq.plot(jq(".chart"),
           [ { data: sin, label: "sin(x)"}, { data: cos, label: "cos(x)" } ], {
               series: {
                   lines: { show: true },
                   points: { show: true }
               },
               grid: { hoverable: true, clickable: true },
               yaxis: { min: -1.2, max: 1.2 }
             });

    function showTooltip(x, y, contents) {
        jq('<div id="tooltip">' + contents + '</div>').css( {
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5,
            border: '1px solid #258dde',
            padding: '2px',
            'background-color': '#ffffff',
            opacity: 0.80
        }).appendTo("body").fadeIn(200);
    }

    var previousPoint = null;
    jq(".chart").bind("plothover", function (event, pos, item) {
        jq("#x").text(pos.x.toFixed(2));
        jq("#y").text(pos.y.toFixed(2));

        if (jq("#enableTooltip:checked").length == 0) {
            if (item) {
                if (previousPoint != item.dataIndex) {
                    previousPoint = item.dataIndex;

                    jq("#tooltip").remove();
                    var x = item.datapoint[0].toFixed(2),
                        y = item.datapoint[1].toFixed(2);

                    showTooltip(item.pageX, item.pageY,
                                item.series.label + " of " + x + " = " + y);
                }
            }
            else {
                jq("#tooltip").remove();
                previousPoint = null;
            }
        }
    });

    jq(".chart").bind("plotclick", function (event, pos, item) {
        if (item) {
            jq("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
            plot.highlight(item.series, item.datapoint);
        }
    });

});


//* BAR *//


jq(function () {
    var previousPoint;

    var d1 = [];
    for (var i = 0; i <= 10; i += 1)
        d1.push([i, parseInt(Math.random() * 30)]);

    var d2 = [];
    for (var i = 0; i <= 10; i += 1)
        d2.push([i, parseInt(Math.random() * 30)]);

    var d3 = [];
    for (var i = 0; i <= 10; i += 1)
        d3.push([i, parseInt(Math.random() * 30)]);

    var ds = new Array();

    ds.push({
        data:d1,
        bars: {
            show: true,
            barWidth: 0.2,
            order: 1,
            lineWidth : 2
        }
    });
    ds.push({
        data:d2,
        bars: {
            show: true,
            barWidth: 0.2,
            order: 2
        }
    });
    ds.push({
        data:d3,
        bars: {
            show: true,
            barWidth: 0.2,
            order: 3
        }
    });

    //tooltip function
    function showTooltip(x, y, contents, areAbsoluteXY) {
        var rootElt = 'body';

        jq('<div id="tooltip" class="tooltip-with-bg">' + contents + '</div>').css( {
            position: 'absolute',
            display: 'none',
            'z-index':'1010',
            top: y,
            left: x,
			border: '1px solid #258dde',
            padding: '2px',
            'background-color': '#ffffff',
        }).prependTo(rootElt).show();
    }

    //Display graph
    jq.plot(jq(".bars"), ds, {
        grid:{
            hoverable:true
        }
    });

    //Display horizontal graph
    var d1_h = [];
    for (var i = 0; i <= 5; i += 1)
        d1_h.push([parseInt(Math.random() * 30),i ]);

    var d2_h = [];
    for (var i = 0; i <= 5; i += 1)
        d2_h.push([parseInt(Math.random() * 30),i ]);

    var d3_h = [];
    for (var i = 0; i <= 5; i += 1)
        d3_h.push([ parseInt(Math.random() * 30),i]);

    var ds_h = new Array();
    ds_h.push({
        data:d1_h,
        bars: {
            horizontal:true,
            show: true,
            barWidth: 0.2,
            order: 1,
            lineWidth : 2

        }
    });
ds_h.push({
    data:d2_h,
    bars: {
        horizontal:true,
        show: true,
        barWidth: 0.2,
        order: 2
    }
});
ds_h.push({
    data:d3_h,
    bars: {
        horizontal:true,
        show: true,
        barWidth: 0.2,
        order: 3
    }
});


//add tooltip event
jq(".bars").bind("plothover", function (event, pos, item) {
    if (item) {
        if (previousPoint != item.datapoint) {
            previousPoint = item.datapoint;

            //delete de precedente tooltip
            jq('.tooltip-with-bg').remove();

            var x = item.datapoint[0];

            //All the bars concerning a same x value must display a tooltip with this value and not the shifted value
            if(item.series.bars.order){
                for(var i=0; i < item.series.data.length; i++){
                    if(item.series.data[i][3] == item.datapoint[0])
                        x = item.series.data[i][0];
                }
            }

            var y = item.datapoint[1];

            showTooltip(item.pageX+5, item.pageY+5,x + " = " + y);

        }
    }
    else {
        jq('.tooltip-with-bg').remove();
        previousPoint = null;
    }

});



/* Pie charts */

	jq(function () {
		var data = [];
		var series = Math.floor(Math.random()*10)+1;
		for( var i = 0; i<series; i++)
		{
			data[i] = { label: "Series"+(i+1), data: Math.floor(Math.random()*100)+1 }
		}

	jq.plot(jq("#graph1"), data,
	{
			series: {
				pie: {
					show: true,
					radius: 1,
					label: {
						show: true,
						radius: 2/3,
						formatter: function(label, series){
							return '<div style="font-size:11px;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
						},
						threshold: 0.1
					}
				}
			},
			legend: {
				show: false
			},
			grid: {
				hoverable: false,
				clickable: true
			},
	});
	jq("#interactive").bind("plothover", pieHover);
	jq("#interactive").bind("plotclick", pieClick);

	jq.plot(jq("#graph2"), data,
	{
			series: {
				pie: {
					show: true,
					radius:300,
					label: {
						show: true,
						formatter: function(label, series){
							return '<div style="font-size:11px;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
						},
						threshold: 0.1
					}
				}
			},
			legend: {
				show: false
			},
			grid: {
				hoverable: false,
				clickable: true
			}
	});
	jq("#interactive").bind("plothover", pieHover);
	jq("#interactive").bind("plotclick", pieClick);
	});

	function pieHover(event, pos, obj)
	{
		if (!obj)
					return;
		percent = parseFloat(obj.series.percent).toFixed(2);
		jq("#hover").html('<span style="font-weight: bold; color: '+obj.series.color+'">'+obj.series.label+' ('+percent+'%)</span>');
	}
	function pieClick(event, pos, obj)
	{
		if (!obj)
					return;
		percent = parseFloat(obj.series.percent).toFixed(2);
		alert(''+obj.series.label+': '+percent+'%');
	}


});
//////////////////////