
let originalLog, originalError;
let outputs = [];
let errors = [];

window.setupConsoleInterceptor = function() {

    originalLog = console.log;
    originalError = console.error;
    outputs = [];
    errors = [];

    console.log = function(...args) {
        outputs.push(args.map(String).join(' '));
    };

    console.error = function(...args) {
        errors.push(args.map(String).join(' '));
    };
};

window.executeScriptWithCapture = function(script) {
    let exitCode = 0;

    try {
        // NOTE: this is a horrible idea.
        // don't use eval
        eval(script);
    } catch (e) {
        errors.push(e.message || String(e));
        exitCode = 1;
    }

    return {
        outputs: outputs,
        errors: errors,
        exitCode: exitCode
    };
};

window.restoreConsole = function() {
    if (originalLog) console.log = originalLog;
    if (originalError) console.error = originalError;
    console.log("Console restored.")
};