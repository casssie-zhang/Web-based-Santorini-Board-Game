import { useEffect, useState } from 'react'
import Grid from './Grid'
import './static/css/Board.css'
import BottomBar from './BottomBar'
import { Alert, Divider, Layout, Button } from 'antd'
import HintBar from './HintBar'

const { Header, Footer, Sider, Content } = Layout

interface Cell {
  text: string
  clazz: string
  link: string
}

function Board () {
  const initCells: Cell[] = []
  for (let i = 0; i < 5; i++) {
    for (let j = 0; j < 5; j++) {
      const cellLink = '/init?x=' + i + '&y=' + j
      initCells.push({ text: '', clazz: 'playable', link: cellLink })
    }
  }

  // initial states
  const [cells, setCells] = useState(initCells)
  const [instr, setInstr] = useState<String>('Player 1 Please Initialize Worker.')
  const [error, setError] = useState<boolean>(false)
  const [skip, setSkip] = useState<boolean>(false)
  const [skipLink, setSkipLink] = useState<string>('')

  const getTurn = (p: any): String => {
    return p.turn
  }
  const getNextAction = (p: any): String => {
    return p.action
  }

  // Check current action can be skipped or not.
  const getSkip = (p: any): void => {
    if (p.skip === '1') {
      setSkip(true)
      setSkipLink(p.skipLink)
    } else {
      setSkip(false)
    }
  }

  const getWinner = (p: any): String | undefined => {
    return p.winner
  }

  const onSkip = () => {
    handleAction(skipLink, 'playable')
  }

  const convertToCell = (p: any) => {
    const newCells: Cell[] = []
    for (let i = 0; i < p.cells.length; i++) {
      const c: Cell = {
        text: p.cells[i].text,
        clazz: p.cells[i].clazz,
        link: p.cells[i].link
      }
      newCells.push(c)
    }
    setCells(newCells)
  }

  const updateInfo = (data: any) => {
    const turn = getTurn(data)
    const action = getNextAction(data)
    const winner = getWinner(data)

    getSkip(data)
    convertToCell(data)

    // update info bar
    if (winner !== undefined) {
      setInstr('Congratulations to ' + winner + '! You won the game!')
    } else {
      setInstr(turn + ' Please ' + action)
    }
  }
  const handleAction = (url: string, clazz: string) => {
    if (clazz !== 'playable') {
      console.log('invalid')
      setError(true)
    } else {
      setError(false)
      console.log(url)
      const response = fetch(url)
      response
        .then(async (data) => await data.json())
        .then((data) => {
          console.log(data)
          updateInfo(data)
        })
    };
  }

  // This gets called at first render.
  useEffect(() => {
    console.log('Use Effect triggered')
    const response = fetch('getboard')
    response
      .then(async (data) => await data.json())
      .then((data) => {
        console.log(data)
        updateInfo(data)
      })
  }, [])

  const renderSquare = (i: number) => {
    return (
      <Grid
        text={cells[i].text}
        link={cells[i].link}
        clazz={cells[i].clazz}
        onClick={handleAction}
      />
    )
  }

  return (
    <Layout style={{ width: '100%', background: 'white' }}>
      <Header className='CommonHeader'>
        <div style={{ margin: '0.5rem', padding: '0.5rem' }}>
          <Alert message='Next Step' description={instr} type='info' className='infobar' showIcon />
          {skip && <Button className='skip' type='primary' onClick={onSkip}>Skip</Button>}
        </div>
      </Header>
      <Layout>
        <Sider id='sider'>
          <div id='godhint'>
            <div>Player 1 chooses: </div>
            <p> {localStorage.getItem('play1god')} </p>
            <div>Player 2 chooses: </div>
            <p> {localStorage.getItem('play2god')} </p>
          </div>
        </Sider>
        <Content id='content'>
          <Divider>Board</Divider>
          <HintBar />
          <div className='board'>
            <div>{error &&
              <Alert
                message='Error' description='Invalid Action! Please Read <Next Step>.'
                className='infobar' type='error' showIcon
              />}
            </div>
            <div className='board-row'>
              {renderSquare(0)}
              {renderSquare(1)}
              {renderSquare(2)}
              {renderSquare(3)}
              {renderSquare(4)}
            </div>
            <div className='board-row'>
              {renderSquare(5)}
              {renderSquare(6)}
              {renderSquare(7)}
              {renderSquare(8)}
              {renderSquare(9)}
            </div>
            <div className='board-row'>
              {renderSquare(10)}
              {renderSquare(11)}
              {renderSquare(12)}
              {renderSquare(13)}
              {renderSquare(14)}
            </div>
            <div className='board-row'>
              {renderSquare(15)}
              {renderSquare(16)}
              {renderSquare(17)}
              {renderSquare(18)}
              {renderSquare(19)}
            </div>
            <div className='board-row'>
              {renderSquare(20)}
              {renderSquare(21)}
              {renderSquare(22)}
              {renderSquare(23)}
              {renderSquare(24)}
            </div>
          </div>
        </Content>
      </Layout>
      <Footer>
        <BottomBar />
      </Footer>
    </Layout>

  )
}

export default Board
