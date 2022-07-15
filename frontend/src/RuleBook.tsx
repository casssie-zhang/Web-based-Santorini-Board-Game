import { Content, Footer, Header } from 'antd/lib/layout/layout'
import { Layout, Button, Row, Col } from 'antd'
import { GodCard, cardData } from './Gods'
import { useNavigate } from 'react-router-dom'

function RuleBook () {
  const navigate = useNavigate()
  const onBoard = () => {
    navigate('/board')
  }
  return (
    <Layout>
      <Header className='CommonHeader'>
        <div style={{ fontSize: '2.5em' }}> Hi, Welcome to Santorini! </div>
        <div style={{ fontSize: '2em' }}> Read rules </div>
      </Header>
      <Content>
        <Row gutter={[24, 24]}>
          {
                    cardData.map((card) => {
                      return (
                        <Col>
                          <GodCard cardInfo={card} onSelect={() => {}} />
                        </Col>
                      )
                    })
                }
        </Row>
      </Content>
      <Footer>
        <Button className='skip' type='primary' onClick={onBoard}>
          Back to board
        </Button>
      </Footer>

    </Layout>
  )
}

export default RuleBook
