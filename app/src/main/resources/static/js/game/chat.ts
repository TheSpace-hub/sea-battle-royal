export function addChatMessage(username: string, message: string) {
    const chatContainer: HTMLDivElement = document.getElementById('chat-messages') as HTMLDivElement
    if (!chatContainer) return;

    const messageElement: HTMLDivElement = document.createElement('div')
    messageElement.classList.add('chat-message', 'mb-2')
    messageElement.innerHTML = `<strong>${escapeHtml(username)}:</strong> ${escapeHtml(message)}`

    chatContainer.appendChild(messageElement)
    chatContainer.scrollTop = chatContainer.scrollHeight
}

function escapeHtml(text: string): string {
    const div: HTMLDivElement = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}