$(document).ready(function () {
  $('#createButton').click(function () {
    $.ajax({
      type: 'POST',
      url: '/values',
      data: '{ "value" : "' + $('#createValue').val() + '" }',
      success: function (response) {
        var now = new Date();
        debugger;
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#createMessage').text(currentTime + ": OK");
      },
      error: function (data) {
        debugger;
        var now = new Date();
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#createMessage').text(currentTime + ": " + "ERROR");
      }
    });
  });

  $('#getButton').click(function () {
    $.ajax({
      type: 'GET',
      url: '/values',
      success: function (data) {
        var results = document.getElementById("results");
        debugger;
        if (results) {
          results.innerHTML = "";
          $.each(JSON.parse(data), function (i, item) {
            $('#results').append("<li>" + item + "</li>");
          });
        }
        var now = new Date();
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#getMessage').text(currentTime + ": OK");
      },
      error: function (data) {
        var now = new Date();
        debugger;
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#getMessage').text(currentTime + "[ERROR]: " + data.error);
      }
    });
  });

  $('#deleteButton').click(function () {
    $.ajax({
      type: 'DELETE',
      url: '/values',
      data: '{ "value" : "' + $('#deleteValue').val() + '" }',
      success: function (response) {
        var now = new Date();
        debugger;
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#deleteMessage').text(currentTime + ": OK");
      },
      error: function (data) {
        var now = new Date();
        debugger;
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#deleteMessage').text(currentTime + "[ERROR]: " + data.error);
      }
    });
  });

  $('#putButton').click(function () {
    $.ajax({
      type: 'PUT',
      url: '/values',
      data: '{ "value" : "' + $('#putValue').val() + '", "newValue" : "' + $(
          '#putNewValue').val() + '" }',
      success: function (response) {
        debugger;
        var now = new Date();
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#putMessage').text(currentTime + ": OK");
      },
      error: function (response) {
        debugger;
        var now = new Date();
        var currentTime = "[" + now.getHours() + ":" + now.getMinutes() + ":"
            + now.getSeconds() + "]";
        $('#putMessage').text(currentTime + ": " + response);
      }
    });
  });
});