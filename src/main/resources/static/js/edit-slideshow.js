function addExistingItemToSlideshowList() {
    // Get the details of the selected item
    var selectedItem = document.getElementsByClassName('selected')[0];
    var dataId = selectedItem.getAttribute('data-id');
    var dataUrl = selectedItem.getAttribute('data-url');
    $('#selections').append(`
        <li data-id="${dataId}">
            <div class="slideshow-list-item">
                <img class="image-list-thumbnail" src="${dataUrl}"/>
                <div class="infobox">
                    <button class="delete">Delete</button>
                </div>
             </div>
        </li>
    `);

    $('#selections li button.delete').last().click(removeItemFromImageList);
}

function updateSlideshow() {
    var listElements = $('#selections li');
    var slideIds = [];
    for (var i = 0; i < listElements.length; i++) {
        slideIds.push(listElements[i].getAttribute('data-id'));
    }
    var slideshowId = window.location.pathname.split("/").pop();

    fetch(`/edit-slideshow/${slideshowId}/added-image`,{method:'post', headers: {'content-type': 'application/json'}, body: JSON.stringify({
            slideIds: slideIds
        })})
        .then(function(response) {
            if (!response.ok) throw new Error(response.statusText)
        })
        .then(function() { location.reload() })
        .catch(function (err) {
            console.error(err);
        });
}