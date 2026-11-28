
const healthButton = document.getElementById("healthButton");
const healthResult = document.getElementById("healthResult");

healthButton.addEventListener("click", async () => {
    healthResult.textContent = "Checking backend...";

    try {
        const response = await fetch("/api/health");

        if (!response.ok) {
            throw new Error("Backend request failed");
        }

        const data = await response.text();
        healthResult.textContent = data;
    } catch (error) {
        healthResult.textContent = "Backend is currently unavailable.";
        console.error("Health check error:", error);
    }
});
