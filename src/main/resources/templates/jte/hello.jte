@param String message

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Invoke Java Method</title>
    <link href="/css/main.css" rel="stylesheet">
    <script src="https://unpkg.com/htmx.org@2.0.0"></script>
</head>
<body class="bg-slate-900 min-h-screen flex items-center justify-center p-4">

<div class="bg-white p-8 rounded-2xl shadow-2xl max-w-sm w-full border border-slate-100">
    <div class="text-center mb-6">
        <h1 class="text-3xl font-black text-slate-800 tracking-tight">SKPG VM Server</h1>
        <p class="text-sm text-slate-500 mt-1">${message}</p>
    </div>

    <div class="space-y-4">
        <!-- HTMX MAGIC BUTTON:
             hx-post  -> Calls the Java method mapped to /time
             hx-target -> Swaps the HTML inside the #timeDisplay div below with the Java response
        -->
        <button hx-post="/time"
                hx-target="#timeDisplay"
                class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-3 px-4 rounded-xl text-sm transition duration-150 shadow-md hover:shadow-lg active:scale-[0.98]">
            Invoke Get System Time
        </button>

        <!-- This is the container where Java's HTML response will get cleanly injected -->
        <div id="timeDisplay">
            <p class="text-center text-xs text-slate-400 italic mt-4">
                Click the button to ping the JVM.
            </p>
        </div>
    </div>

    <div class="mt-8 pt-4 border-t border-slate-100 flex justify-between text-[10px] font-bold text-slate-400 uppercase tracking-wider">
        <span>Status: Online</span>
        <span>Target: Headless JRE</span>
    </div>
</div>

</body>
</html>