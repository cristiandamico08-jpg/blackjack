function download(){
    const link = document.createElement('a');
    link.href = 'https://github.com/cristiandamico08-jpg/blackjack/releases/download/nuovo_setup/setupBlackJack.exe';
    link.download = 'setupBlackJack.exe';
    link.click();
}