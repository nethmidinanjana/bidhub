function startCountDown() {
    const countDownElements = document.querySelectorAll(".countdown[data-end-time]");

    countDownElements.forEach(el => {
        const endTimeString = el.dataset.endTime;
        const countDownSpan = el.querySelector(".time");
        const endTime = new Date(endTimeString);

        function updateCountDown() {
            const now = new Date();
            const timeDiff = endTime - now;

            if (timeDiff <= 0) {
                clearInterval(timer);
                window.location.reload();
                return;
            }

            const seconds = Math.floor(timeDiff / 1000);
            const days = Math.floor(seconds / (3600 * 24));
            const hours = Math.floor((seconds % (3600 * 24)) / 3600);
            const minutes = Math.floor((seconds % 3600) / 60);
            const secs = seconds % 60;

            countDownSpan.textContent =
                days + " days " + hours + " hours " + minutes + " minutes " + secs + " seconds.";
        }

        updateCountDown();
        const timer = setInterval(updateCountDown, 1000);
    });
}

window.addEventListener("DOMContentLoaded", startCountDown);


function formatTimeAgo(dateString) {
    const now = new Date();
    const date = new Date(dateString);
    const diff = Math.floor((now - date) / 1000);

    if (diff < 60) return `${diff} seconds ago`;
    if (diff < 3600) return `${Math.floor(diff / 60)} minutes ago`;
    if (diff < 86400) return `${Math.floor(diff / 3600)} hours ago`;
    return `${Math.floor(diff / 86400)} days ago`;
}

function placeBid(itemId){

    const bidAmountInput = document.getElementById("bid-amount");
    bidAmount = bidAmountInput.value;

    console.log("Bid amount: "+ bidAmount);

    if (!bidAmount || isNaN(bidAmount) || bidAmount <= 0) {
        alert("Please enter a valid bid amount.");
        return;
    }


    const form = new FormData();
    form.append("itemId", itemId);
    form.append("bidAmount", bidAmount);

    const req = new XMLHttpRequest();

    req.onreadystatechange = function (){
        if(req.readyState === 4){
            const res = req.responseText;
            if(res === "success"){
                alert("Bid placed successfully");
                bidAmountInput.value = "";
            }else if(res === "login required"){
                alert("Login required.");
                window.location.href = "register.jsp";
            }else if (res === "Bid too low or auction ended"){
                alert("Bid too low or auction ended.");
            } else{
                console.log(res);
                alert("Error placing bid");
            }
        }
    }

    req.open("POST", "place-bid", true);
    req.send(form);
}

const ws = new WebSocket("ws://localhost:8080/bidhub-app/live-bids");

ws.onmessage = function (event){
    const bid = JSON.parse(event.data);

    const currentBid = document.getElementById("current-bid");
    const bidAmount = document.getElementById("bid-amount");
    const noBids = document.getElementById("no-bids");

    console.log("Live bid: ", bid);

    const trimmedTimestamp = bid.timestamp.slice(0, 23);
    const formattedTime = new Date(trimmedTimestamp).toLocaleString("en-IN", {
        dateStyle: "medium",
        timeStyle: "short"
    })

    if(noBids){
        noBids.remove();
    }

    const bidEntry = document.createElement("div");
    bidEntry.className = "bid-entry";
    bidEntry.innerHTML = `
    <span class="bidder">${bid.bidderEmail.split('@')[0]}</span>
    <span class="bid-amount">Rs. ${bid.amount.toLocaleString()}.00</span>
    <span class="bid-time">${formattedTime}</span>
    `;

    currentBid.innerHTML = "Rs. "+bid.amount.toLocaleString()+".00";
    bidAmount.min = bid.amount + 1;

    const container = document.querySelector(".bid-history");
    container.prepend(bidEntry);
}

ws.onopen = function () {
    console.log("WebSocket connected");
};

ws.onclose = function () {
    console.log("WebSocket disconnected");
};

ws.onerror = function (err) {
    console.error("WebSocket error", err);
};
