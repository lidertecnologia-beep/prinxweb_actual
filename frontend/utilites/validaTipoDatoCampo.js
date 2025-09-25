export function campoSoloNumero(event) {

    if (event.key === 'Delete' || event.key === 'Backspace') {
        return true;
    }

    if (event.keyCode <= 47 || event.keyCode >= 59) {
        event.preventDefault();
        return false;
    } 
        
    return true;
    
}
