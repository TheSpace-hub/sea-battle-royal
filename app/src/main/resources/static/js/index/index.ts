// @ts-ignore
import {Client} from '@stomp/stompjs';
// @ts-ignore
import SockJS from 'sockjs-client'

document.querySelector('#create-game')?.addEventListener('click', create_game)

const WEBSOCKET_URL = 'http://localhost:8080/websocket'

class WebSocketService {
    private client: Client

    constructor() {
        this.client = new Client({
            webSocketFactory: () => new SockJS(WEBSOCKET_URL),
            debug: (msg: string) => {
                console.log(msg)
            }
        })
    }

    public activate() {
        this.client.onConnect = (frame: any) => {
            this.client.subscribe('/topic/updating-the-list-of-games', () => {
                generateListOfGames().then()
            })
        }

        this.client.activate()
    }
}

document.addEventListener("DOMContentLoaded", () => {
    new WebSocketService().activate()
})

async function generateListOfGames() {
    try {
        const response = await fetch('/api/list-of-games', {
            method: 'GET'
        })
        if (!response.ok) {
            console.error(response.statusText)
        }
        const data: Array<{ [gameId: string]: object }> = await response.json()

        const listOfGames = document.querySelector('#list-of-games')
        while (listOfGames?.firstChild) {
            listOfGames.removeChild(listOfGames?.firstChild)
        }
        for (const [gameId, game] of Object.entries(data)) {
            addGameToListOfGames(gameId, (game['players'] as []).length, game['number-of-players'] as unknown as number)
        }
    } catch (error) {
        console.error(error)
    }
}

function addGameToListOfGames(id: string, players: number, numberOfPlayers: number) {
    document.querySelector('#list-of-games')?.insertAdjacentHTML('beforeend', createItem(id, players, numberOfPlayers))
    document.querySelector(`#game-${id}`)?.addEventListener('click', () => {
        console.log('open game ' + id)
    })
}

function createItem(id: string, players: number, numberOfPlayers: number): string {
    return `
<li class="list-group-item align-items-center justify-content-between d-flex">
    <a href="#" id="game-${id}" class="text-decoration-none">Бой #${id.toUpperCase()}</a>
    <span class="badge bg-primary rounded-pill">${players} / ${numberOfPlayers}</span>
</li>
`
}

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
