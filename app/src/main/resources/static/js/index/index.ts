document.querySelector('#create-game')?.addEventListener('click', create_game)

async function create_game() {
    const numberOfPlayers = (document.querySelector('#players-count') as HTMLSelectElement).value

    const response = await fetch('/api/create-game', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'number-of-players': numberOfPlayers
        })
    })

    if (!response.ok) {
        console.error(response.statusText)
    }

    console.log((await response.json())['id'])
}
