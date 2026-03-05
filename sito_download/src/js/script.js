
document.getElementById('downloadWindows').addEventListener('click', ()=>{
    const link = document.createElement('a');
    link.href = 'https://github.com/cristiandamico08-jpg/blackjack/releases/download/nuovo_setup/setupBlackJack.exe';
    link.download = 'setupBlackJack.exe';
    link.click();
});

document.getElementById('download64').addEventListener('click', ()=>{
    const link = document.createElement('a');
    link.href = 'https://github.com/cristiandamico08-jpg/blackjack/releases/download/nuovo_setup/setupBlackJack_x64.dmg';
    link.download = 'setupBlackJack_ARM64.dmg';
    link.click();
});

document.getElementById('downloadARM64').addEventListener('click', ()=>{
    const link = document.createElement('a');
    link.href = 'https://github.com/cristiandamico08-jpg/blackjack/releases/download/nuovo_setup/setupBlackJack_ARM64.dmg';
    link.download = 'setupBlackJack_x64.dmg';
    link.click();
});

document.getElementById('downloadJavaWindows').addEventListener('click', ()=>{
    const link = document.createElement('a');
    link.href = 'https://download.oracle.com/java/25/latest/jdk-25_windows-x64_bin.exe';
    link.download = 'jdk-25_windows-x64_bin.exe';
    link.click();
});

document.getElementById('downloadJava64').addEventListener('click', ()=>{
    const link = document.createElement('a');
    link.href = 'https://download.oracle.com/java/25/latest/jdk-25_macos-x64_bin.dmg';
    link.download = 'jdk-25_macos-x64_bin.dmg';
    link.click();
});

document.getElementById('downloadJavaARM64').addEventListener('click', ()=>{
    const link = document.createElement('a');
    link.href = 'https://download.oracle.com/java/25/latest/jdk-25_macos-aarch64_bin.dmg';
    link.download = 'jdk-25_macos-aarch64_bin.dmg';
    link.click();
});

document.getElementById('github').addEventListener('click', ()=>{
    window.open('https://github.com/cristiandamico08-jpg/blackjack', '_blank');
});