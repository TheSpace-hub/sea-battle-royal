const letters: string[] = ['А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К']

const params = new URLSearchParams(window.location.search)
const youUsername: string = params.get('username') as string

document.querySelector('#the-username-of-the-main-player-in-the-fields')!.innerHTML = youUsername

export function initBattlefield() {
    const battlefield: HTMLDivElement = document.getElementById('battlefield') as HTMLDivElement

    battlefield.innerHTML = ''

    const topLeftCell = document.createElement('div')
    topLeftCell.className = 'cell coordinate'
    battlefield.appendChild(topLeftCell)


    for (let i = 0; i < 10; i++) {
        const letterCell: HTMLDivElement = document.createElement('div') as HTMLDivElement
        letterCell.className = 'cell coordinate'
        letterCell.textContent = letters[i] as string
        battlefield.appendChild(letterCell)
    }

    for (let row = 0; row < 10; row++) {
        const numberCell = document.createElement('div')
        numberCell.className = 'cell coordinate'
        numberCell.textContent = (row + 1).toString()
        battlefield.appendChild(numberCell)

        for (let col = 0; col < 10; col++) {
            const cell: HTMLDivElement = document.createElement('div')
            cell.className = 'cell'
            cell.dataset.row = row.toString()
            cell.dataset.col = col.toString()

            cell.addEventListener('click', function () {
                handleCellClick(this)
            })

            battlefield.appendChild(cell)
        }
    }

    // document.getElementById(`mode-player-you`).addEventListener('click', function () {
    //     setMode(`player`, 0)
    // })
    function handleCellClick(cell: HTMLDivElement) {
        const x: number = parseInt(cell.dataset.col as string)
        const y: number = parseInt(cell.dataset.row as string)
        console.log(x, y)
        // if (getStatus() === gameStatusTypes.WAITING_SELF_START) {
        //     const x: number = parseInt(cell.dataset.col as string)
        //     const y: number = parseInt(cell.dataset.row as string)
        //     if (cell.classList.contains('ship')) {
        //         cell.classList.remove('ship')
        //         players[0].field.field[y][x] = 'EMPTY'
        //     } else {
        //         cell.classList.add('ship')
        //         players[0].field.field[y][x] = 'SHIP'
        //     }
        // } else if (getStatus() === gameStatusTypes.WAITING_SELF_MOVE) {
        //     const x = parseInt(cell.dataset.col)
        //     const y = parseInt(cell.dataset.row)
        //     attack(x, y);
        // }
    }
}