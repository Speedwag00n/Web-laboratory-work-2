{
    $("#task-chart").bind("click", function(e) {
        let R = $('#computation-form').find('input[name="R"]').val();
        if (!(isPresented(R, "R", false) && validateTextParam(R, "R", false))) {
            showWarning("Необходимо указать валидное значение R для использования интерактивного режима графика", "chart");
            return;
        } else {
            showWarning("", "chart");
        }

        let canvas = e.target;

        let originalX = e.pageX - canvas.offsetLeft;
        let originalY = e.pageY - canvas.offsetTop;

        let X = toComputingX(originalX, R);
        let Y = toComputingY(originalY, R);

        $.ajax({
            url: "check",
            type: "post",
            dataType: "html",
            data:
                {
                    "X": String(X).substring(0, 10),
                    "Y": String(Y).substring(0, 10),
                    "R": String(R).substring(0, 10)
                },
            success: function(response) {
                let hit = $(response).find(".result-hit").eq(0).text().trim();
                if (hit == "Да") {
                    drawPoint(canvas, originalX, originalY, "GreenYellow");
                } else {
                    drawPoint(canvas, originalX, originalY, "Red");
                }
            }
        });
    });
}