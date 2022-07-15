import { GodCard, cardData } from './Gods'
import { Layout, Row, Col, Button } from 'antd'
import { Content, Header } from 'antd/lib/layout/layout'
import { useState } from 'react'
import { useNavigate } from 'react-router'
import 'antd/dist/antd.css'
import './static/css/Board.css'

/**
 * Choose god cards page.
 * @returns functional component for choosing god before starting game.
 */
function ChooseCard () {
  const [player1God, setPlayer1God] = useState<string|null>(null)
  const [player2God, setPlayer2God] = useState<string|null>(null)
  const navigate = useNavigate()

  const handlePlayer1Select = (god: string) => {
    setPlayer1God(god)
  }

  const handlePlayer2Select = (god: string) => {
    setPlayer2God(god)
  }

  /**
   * The action triggered by clicking start game.
   * Communicate with the server to create a new game with selected gods.
   */
  const onStartGame = () => {
    if (player1God === null) setPlayer1God('nogod')
    if (player2God === null) setPlayer2God('nogod')

    // player1 and player cannot choose the same god
    // or they can both opt not to choose god cards.
    if (player1God !== player2God ||
      (player1God === 'nogod' && player2God === 'nogod')) {
      const gameStartLink = '/newgame?player1=' + player1God + '&player2=' + player2God
      const response = fetch(gameStartLink)
      response
        .then(async (data) => await data.json())
        .then((data) => {
          console.log(data)
          localStorage.setItem('play1god', player1God === null ? 'No God Chosen' : player1God)
          localStorage.setItem('play2god', player2God === null ? 'No God Chosen' : player2God)
          navigate('/board') // navigate to board.
        })
    }
  }

  const contentStyle = { height: '100%', marginLeft: '50px', marginTop: '20px' }

  return (

    <Layout>
      <Header className='CommonHeader'>
        <div style={{ fontSize: '2.5em' }}> Hi, Welcome to Santorini! </div>
        <div style={{ fontSize: '2em' }}> To start game, please choose god cards for players</div>
      </Header>
      <Content style={contentStyle}>
        <h2>Player 1 Choose Card: {player1God}</h2>
        <Row gutter={[24, 24]}>
          {
              cardData.map((card) => {
                if (card.title !== player2God) {
                  return (
                    <Col>
                      <GodCard cardInfo={card} onSelect={handlePlayer1Select} />
                    </Col>
                  )
                }
              })
            }
        </Row>
        <h2> Player 2 Choose Card: {player2God} </h2>
        <Row gutter={[24, 24]}>
          {
              cardData.map((card) => {
                // console.log(player1God,card.title);
                if (card.title !== player1God) {
                  return (
                    <Col>
                      <GodCard cardInfo={card} onSelect={handlePlayer2Select} />
                    </Col>
                  )
                }
              }
              )
}
        </Row>
        <Button
          type='primary' shape='round' size='large'
          onClick={onStartGame} style={{ margin: '20px' }}
        >
          Start Game
        </Button>
      </Content>
    </Layout>
  )
}

export default ChooseCard
