/*global jQuery */
/*jslint vars:true, indent:2 */

(function($) {
  $(function() {
    var $upload = $('<iframe/>').load(
        function() {
          var $this = $(this);
          var $form = $('<form method="post" enctype="multipart/form-data"/>')
              .attr('action', 'photo');
          var $uploadBtn = $('<input id="upload" name="file" type="file"/>');
          $uploadBtn.change(function() {
            console.log('abc');
            $form.submit();
          }).appendTo($form);
          $('<input/>').attr('name', 'callback').val('upload').appendTo($form);
          $this.css({
            display : 'none'
          }).contents().find('body').append($form);
        }).appendTo(document.body);
    $('#photo-btn').click(function(event) {
      event.preventDefault();
      $upload.contents().find('[type="file"]').click();
    });
    window.upload = function(json) {
      $('[name="photo"]').val(json.url);
      $('#avatar').attr('src', json.url);
    };
  });
}(jQuery));