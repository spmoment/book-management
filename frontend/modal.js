$(document).foundation();

var $modalCrop = $('#modalCrop');
var $image = $modalCrop.find('.js-cropimage');
var $preview = $modalCrop.find('.crop__preview');

var initCrop = function() {
    var url = $image.attr('src');
      $preview.empty().html('<img src="' + url + '">');

      $image.cropper({
        aspectRatio: 16 / 9,
        preview: $preview.selector,
        crop: function(data) {
          // Output the result data for cropping image.
        }
      });
};

var destroyCrop = function() {
  $image.cropper('destroy');
};

$modalCrop
  .on('opened.fndtn.reveal', initCrop)
  .on('closed.fndtn.reveal', destroyCrop);

$('.js-modal').on('click', function() {
  console.log('trigger modal open');
  $modalCrop.foundation('reveal', 'open');
});

