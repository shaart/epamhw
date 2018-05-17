const VALUES_PATH = '/values';

function writeStatus(element, message) {
  let now = new Date();
  let hours = now.getHours();
  let minutes = now.getMinutes();
  let seconds = now.getSeconds();
  hours = "" + (hours < 10 ? "0" + hours : hours);
  minutes = "" + (minutes < 10 ? "0" + minutes : minutes);
  seconds = "" + (seconds < 10 ? "0" + seconds : seconds);
  let currentTime = "[" + hours + ":" + minutes + ":" + seconds + "]";
  $(element).text(currentTime + ": " + message);
}

$(document).ready(function () {
  $('#createButton').click(function () {
    $.ajax({
      type: 'POST',
      url: VALUES_PATH,
      data: '{ "name" : "' + $('#createValue').val() + '" }',
      success: function (data) {
        writeStatus('#createStatus', "OK " +
            (data ? "Added: [" + data.id + "] " + data.name : "")
        );
      },
      error: function (data) {
        writeStatus('#createStatus', "ERROR " +
            (data.status ? data.status : "")
        );
      }
    });
  });

  $('#getButton').click(function () {
    $.ajax({
      type: 'GET',
      url: VALUES_PATH,
      success: function (data) {
        let results = document.getElementById("results");
        if (results) {
          results.innerHTML = "";
          for (let i = 0; i < data.length; i++) {
            $('#results').append("<li>[" + data[i].id + "] "
                + data[i].name + "</li>");
          }
        }
        if (!results || results.innerHTML === "") {
          results.innerHTML = "No results";
        }
        writeStatus('#getStatus', "OK. Results count: " + data.length);
      },
      error: function (data) {
        writeStatus('#getStatus', "ERROR " +
            (data && data.status ? data.status : "")
        );
      }
    });
  });

  $('#deleteButton').click(function () {
    $.ajax({
      type: 'DELETE',
      url: VALUES_PATH + '/' + $('#deleteValue').val(),
      success: function (data) {
        writeStatus('#deleteStatus', "OK " +
            (data ? "Deleted [" + data.id + "] " + data.name : "")
        );
      },
      error: function (data) {
        writeStatus('#deleteStatus', "ERROR " +
            (data && data.status ? data.status : "")
        )
      }
    });
  });

  $('#putButton').click(function () {
    let putValueId = $('#putValueId').val();
    let putValueName = $('#putValueName').val();
    $.ajax({
      type: 'PUT',
      url: VALUES_PATH + '/' + putValueId,
      data: '{ "id" : "' + putValueId
      + '", "name" : "' + putValueName + '" }',
      success: function (data) {
        writeStatus('#putStatus', "OK " +
            (data ? "Put: [" + data.id + "] " + data.name : "")
        );
      },
      error: function (data) {
        writeStatus('#putStatus', "ERROR " +
            (data && data.status ? data.status : "")
        );
      }
    });
  });
});