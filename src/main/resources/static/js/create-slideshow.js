function createDropdown() {
    var x, i, j, selElmnt, a, b, c;

    /*look for any elements with the class "custom-select":*/
    x = document.getElementsByClassName("custom-select");
    for (i = 0; i < x.length; i++) {
        selElmnt = x[i].getElementsByTagName("select")[0];
        /*for each element, create a new DIV that will act as the selected item:*/
        a = document.createElement("DIV");
        a.setAttribute("class", "select-selected");
        a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
        x[i].appendChild(a);
        /*for each element, create a new DIV that will contain the option list:*/
        b = document.createElement("DIV");
        b.setAttribute("class", "select-items select-hide");
        for (j = 1; j < selElmnt.length; j++) {
            /*for each option in the original select element,
            create a new DIV that will act as an option item:*/
            c = document.createElement("DIV");
            c.innerHTML = selElmnt.options[j].innerHTML;
            c.addEventListener("click", function (e) {
                /*when an item is clicked, update the original select box,
                and the selected item:*/
                var y, i, k, s, h;
                s = this.parentNode.parentNode.getElementsByTagName("select")[0];
                h = this.parentNode.previousSibling;
                for (i = 0; i < s.length; i++) {
                    s.options[i].removeAttribute('class');
                    if (s.options[i].innerHTML === this.innerHTML) {
                        s.options[i].setAttribute('class', 'selected');
                        s.selectedIndex = i;
                        h.innerHTML = this.innerHTML;
                        y = this.parentNode.getElementsByClassName("same-as-selected");
                        for (k = 0; k < y.length; k++) {
                            y[k].removeAttribute("class");
                        }
                        this.setAttribute("class", "same-as-selected");
                        this.setAttribute("url", "this works")
                        break;
                    }
                }
                h.click();
            });
            b.appendChild(c);
        }
        x[i].appendChild(b);
        a.addEventListener("click", function (e) {
            /*when the select box is clicked, close any other select boxes,
            and open/close the current select box:*/
            e.stopPropagation();
            closeAllSelect(this);
            this.nextSibling.classList.toggle("select-hide");
            this.classList.toggle("select-arrow-active");
        });
    }
}

function closeAllSelect(elmnt) {
    /*a function that will close all select boxes in the document,
    except the current select box:*/
    var x, y, i, arrNo = [];
    x = document.getElementsByClassName("select-items");
    y = document.getElementsByClassName("select-selected");
    for (i = 0; i < y.length; i++) {
        if (elmnt == y[i]) {
            arrNo.push(i)
        } else {
            y[i].classList.remove("select-arrow-active");
        }
    }
    for (i = 0; i < x.length; i++) {
        if (arrNo.indexOf(i)) {
            x[i].classList.add("select-hide");
        }
    }
}

function addItemToSlideshowList() {
    // Get the details of the selected item
    var selectedItem = document.getElementsByClassName('selected')[0];
    var dataId = selectedItem.getAttribute('data-id');
    var dataUrl = selectedItem.getAttribute('data-url');
    $('#selections').append(`
        <li data-id="${dataId}">
            <div class="slideshow-list-item">
                <img class="image-list-thumbnail" src="${dataUrl}"/>
                <div class="infobox">
                    <span class="image-name">${selectedItem.getAttribute('data-name')}</span>
                    <div class="move-buttons">
                        <button class="move-up">▲</button>
                        <button class="move-down">▼</button>
                    </div>
                    <button class="delete">Delete</button>
                </div>
             </div>
        </li>
    `);

    $('#selections li button.delete').last().click(removeItemFromImageList);
    $('#selections li button.move-up').last().click(moveUp);
    $('#selections li button.move-down').last().click(moveDown);
}

function removeItemFromImageList() {
    // Remove image from list of all items if added to slideshow list
    $(this).closest('li').remove();
}

function moveUp() {
    var $before = $(this).closest('li').prev();
    $(this).closest('li').after($before);
}

function moveDown() {
    var $after = $(this).closest('li').next();
    $(this).closest('li').before($after);
}

document.addEventListener("click", closeAllSelect);
document.addEventListener('DOMContentLoaded', createDropdown);

function createNewSlideshow() {
    var listElements = $('#selections li');
    var slideIds = [];
    for (var i = 0; i < listElements.length; i++) {
        slideIds.push(listElements[i].getAttribute('data-id'));
    }
    console.log('Got image IDs: ' + slideIds);
    fetch('/admin/slideshows/create',{method:'post', headers: new Headers({'content-type': 'application/json'}), body: JSON.stringify({
        slideshow: { slideshowName: $('#slideshow-name').val() || 'Slideshow' },
        slideIds: slideIds
    })})
    .then(function(response) {
        if (!response.ok) throw new Error(response.statusText)
    })
    .then(function() { window.location = '/admin' })
    .catch(function (err) {
        console.error(err);
    });
}
