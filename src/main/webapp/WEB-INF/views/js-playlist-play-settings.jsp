
<script src="/resources/js/audiojs/jquery.js"></script>
<style>
    .audiojs { width: 480px; !important;}
    .audiojs .scrubber { width: 280px!important;}

    ol { padding: 0px; margin-left: 15px; list-style: decimal-leading-zero inside; color: #ccc; width: 425px; font-size: 0.9em; }
ol li { position: relative; margin: 0 25px; padding: 9px 2px 10px; border-bottom: 1px solid #ccc; cursor: pointer; }
ol li a { display: block; text-indent: -3.3ex; padding: 0px 0px 0px 0px;  }
    ol li a:link {text-decoration: none; !important; }
    ol li a:visited{text-decoration: none; !important;  }
    ol li a:hover{text-decoration: underline; !important;  }
    ol li a:active {text-decoration: underline;  !important;   }

li.playing { color: #444444; text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.7); }
li.playing a { color: #000; }
li.playing:before { content: '#'; width: 14px; height: 14px; padding: 3px; line-height: 14px; margin: 0px; position: absolute; left: -40px; top: 9px; color: #000; font-size: 15px; text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.8); }
</style>
<script>
    $(function() {
        // Setup the player to autoplay the next track
        var a = audiojs.createAll({
            trackEnded: function() {
                var next = $('ol li.playing').next();
                if (!next.length) next = $('ol li').first();
                $('ol li').removeClass('playing');
                next.addClass('playing');//.siblings().removeClass('playing');
                audio.load($('a', next).attr('data-src'));
                audio.play();
            }
        });

        // Load in the first track
        var audio = a[0];
        first = $('ol a').attr('data-src');
        $('ol li').first().addClass('playing');
        audio.load(first);

        // Load in a track on click
        $('ol li').click(function(e) {
            e.preventDefault();
            $('ol li').removeClass('playing');
            $(this).addClass('playing');//.siblings().removeClass('playing');
            audio.load($('a', this).attr('data-src'));
            audio.play();
        });

        // Keyboard shortcuts
        $(document).keydown(function(e) {
            var unicode = e.charCode ? e.charCode : e.keyCode;
            // right arrow
            if (unicode == 39) {
                var next = $('li.playing').next();
                //if (!next.length) next = $('ol li').first();
                if (!next.length) {
                    $('ol li.playing').parent().removeClass('album');
                    next = $('ol.album li').first();
                    $('ol li.playing').parent().addClass('album');
                }
                next.click();
                // back arrow
            } else if (unicode == 37) {
                var prev = $('li.playing').prev();
                if (!prev.length) prev = $('ol li').last();
                prev.click();
                // spacebar
            } else if (unicode == 32) {
                audio.playPause();
            }
        })
    });
</script>