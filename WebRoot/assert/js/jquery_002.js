/**
 * A relatively correct jquery center plugin.
 *
 * @author yfwz100
 * @license BSD-like
 */

(function ($) {
    'use strict';

    var elems = [],
        defaults = {
            minTop : 0,
            topRatio: 0.5,
            leftRatio: 0.5
        };

    $.fn.rcenter = function (options) {
        return $(this).each(function (i, e) {
            var $e = $(e);
            options = $.extend(defaults, options);
            $e.data('rcenter', options);
            if (elems.indexOf($e) < 0) {
                elems.push($e);
            }
        });
    };

    function rCenter() {
        $.each(elems, function (i, $e) {
            var $parent = $e.offsetParent(),
                opt = $e.data('rcenter'),
                w = $e.outerWidth(),
                h = $e.outerHeight(),
                t = $parent.outerHeight() * opt.topRatio - h / 2;

            if (t < opt.minTop) {
                t = opt.minTop;
            }
    
            if ($e.css('position') == 'absolute') {
                var lf = $parent.outerWidth() * opt.leftRatio - w / 2;
                $e.css({
                    top: t + 'px',
                    left: lf + 'px'
                });
            } else {
                $e.css({
                    position: 'relative',
                    margin: 'auto',
                    top: t + 'px'
                });
            }
        });
    }

    $(window).load(function () {
        rCenter();
    });

    $(window).resize(function () {
        rCenter();
    });
}(jQuery));