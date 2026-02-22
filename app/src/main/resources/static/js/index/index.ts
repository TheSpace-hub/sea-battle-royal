document.querySelector('#create-game')?.addEventListener('click', create_game)

async function create_game() {
    const playersCount = (document.querySelector('#players-count') as HTMLSelectElement).value
    console.log(playersCount)

    const response = await fetch('/api/create-game', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'players-count': playersCount
        })
    })

    if (!response.ok) {
        console.error(response.statusText)
    }
}
