export function basicLog(content: string) {
    document.querySelector('#logs')?.insertAdjacentHTML('afterbegin', createBasicLogItem(content))
}

export function playerActionLog(username: string, content: string) {
    document.querySelector('#logs')?.insertAdjacentHTML('afterbegin', createPlayerActionLogItem(username, content))
}

export function importantActionLog(username: string, content: string) {
    document.querySelector('#logs')?.insertAdjacentHTML('afterbegin', createImportantActionLogItem(username, content))
}

function createBasicLogItem(content: string) {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const time = `${hours}:${minutes}`;

    return `
<div class="mb-2">
    <span class="badge bg-secondary">${time}</span>
    <span>${content}</span>
</div>
`
}

function createPlayerActionLogItem(username: string, content: string) {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const time = `${hours}:${minutes}`;

    return `
<div class="mb-2">
    <span class="badge bg-secondary">${time}</span>
    <span>Игрок <strong>${username}</strong> ${content}</span>
</div>
`
}

function createImportantActionLogItem(username: string, content: string) {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const time = `${hours}:${minutes}`;

    return `
<div class="mb-2">
    <span class="badge bg-primary">${time}</span>
    <span>Игрок <strong>${username}</strong> ${content}</span>
</div>
`
}
