$(function () {
    $.ajax({
        url: "/bus/bus",
        type: "get",
        dataType: "json",
        success: function (response) {
            if (response.resultCode === "1") {
                renderLine(response.data.stops, response.data.stopsNum, $("body"));
                renderBus(response.data.buses, $("body"));
            }
        }
    });
});

function renderLine (stops, stopsNum, parentDiv) {
    let ulObj = $("<ul></ul>");
    if ($.isArray(stops) && stops.length === stopsNum) {
        $(stops).each(function (idx, busStop) {
            let li = $("<li><div>" + busStop.stopName + "</div></li>");
            ulObj.append(li);
        })
    }
    $(parentDiv).append(ulObj);
}

function renderBus(buses, parentDiv) {
    let ulObj = $(parentDiv).find("ul");
    if ($.isArray(buses) && buses.length > 0 && ulObj.length > 0) {
        ulObj = ulObj[0];
        $(buses).each(function (idx, bus) {
            let busInfo = bus.split("|");
            let stopIdx = busInfo[2] - 1;
            let running = busInfo[3];
            $(ulObj).find("li:eq(" + stopIdx + ")").append("<i class='" + ((running === "1") ? "run" : "stop") + "'></i>")
        });
    }
}