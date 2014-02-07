$(document).ready(function() {
    var video = $('video').get(0);
    video.src ="android.resource://fr.rouen.Cagliostro.raw.2130968576";
    video.type = "video/mp4";
    video.load();
    video.play();

    $('button').on({'touchstart': function() {
        AndroidFunction.nextEpisode();
    }});
});