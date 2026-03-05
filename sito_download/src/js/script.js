function download(downloadCode){
    switch(downloadCode){
        case 1:
            const link1 = document.createElement('a');
            link1.href = 'https://github.com/cristiandamico08-jpg/blackjack/releases/download/nuovo_setup/setupBlackJack.exe';
            link1.download = 'setupBlackJack.exe';
            link1.click();
            break;
        case 2:
            const link2 = document.createElement('a');
            link2.href = 'https://github.com/cristiandamico08-jpg/blackjack/releases/download/nuovo_setup/setupBlackJack_ARM64.dmg';
            link2.download = 'setupBlackJack_ARM64.dmg';
            link2.click();
            break;
        case 3:
            const link3 = document.createElement('a');
            link3.href = 'https://github.com/cristiandamico08-jpg/blackjack/releases/download/nuovo_setup/setupBlackJack_x64.dmg';
            link3.download = 'setupBlackJack_x64.dmg';
            link3.click();
            break;
    }
    
}