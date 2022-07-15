import { Button, Space } from 'antd'
import { useNavigate } from 'react-router-dom'

/**
 * Bottom bar contains two buttons:
 * 1. a button that triggers new game.
 * 2. a button for the user to review the rules
 */
function BottomBar () {
  const navigate = useNavigate()

  const onStartGame = () => {
    localStorage.clear()
    navigate('/choosegod')
  }

  const onRuleBook = () => {
    localStorage.clear()
    navigate('/rulebook')
  }

  return (
    <div id='bottombar'>
      <Space size={8}>
        <Button
          className='skip' type='primary'
          onClick={onStartGame}
        >
          New Game
        </Button>

        <Button
          className='skip' type='primary'
          onClick={onRuleBook}
        >
          RuleBook
        </Button>

      </Space>
    </div>
  )
}

export default BottomBar
